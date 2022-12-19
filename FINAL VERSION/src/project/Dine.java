package project;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Dine extends Application implements Initializable {

	public class DiningPhilosopherProblem extends Application implements Initializable {

		private static int NO_OF_PHILOSOPHER;
		// private static final int SIMULATION_MILLIS = 100;
		private static final int SIMULATION_MILLIS = 1 * 60 * 1000;
		public String s;

		/**
		 * @param args the command line arguments
		 * @throws InterruptedException
		 */
		public void dine(int N) throws InterruptedException {

			ExecutorService executorService = null;
			NO_OF_PHILOSOPHER = N;
			s = "";

			Philosopher[] philosophers = null;
			try {

				philosophers = new Philosopher[NO_OF_PHILOSOPHER];

				// As many forks as Philosophers
				Chopstick[] chopSticks = new Chopstick[NO_OF_PHILOSOPHER];
				// Cannot do this as it will fill the whole array with the SAME chopstick.
				// Arrays.fill(chopSticks, new ReentrantLock());
				for (int i = 0; i < NO_OF_PHILOSOPHER; i++) {
					chopSticks[i] = new Chopstick(i);
				}

				executorService = Executors.newFixedThreadPool(NO_OF_PHILOSOPHER);

				for (int i = 0; i < NO_OF_PHILOSOPHER; i++) {
					philosophers[i] = new Philosopher(i, chopSticks[i], chopSticks[(i + 1) % NO_OF_PHILOSOPHER]);
					executorService.execute(philosophers[i]);
				}
				// Main thread sleeps till time of simulation
				Thread.sleep(SIMULATION_MILLIS);
				// Stop all philosophers.
				for (Philosopher philosopher : philosophers) {
					philosopher.isTummyFull = true;
				}

			} finally {
				// Close everything down.
				executorService.shutdown();
				// Wait for all thread to finish
				while (!executorService.isTerminated()) {
					Thread.sleep(10000);
				}

				// Time for check
				for (Philosopher philosopher : philosophers) {

					s += philosopher + " => No of Turns to Eat =" + philosopher.getNoOfTurnsToEat() + "\n";
				}
			}
		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void start(Stage arg0) throws Exception {
			// TODO Auto-generated method stub

		}

		public String getOutput() {
			return this.s;
		}

	}

	/* PHILOSOPHERS CLASS */

	public class Philosopher implements Runnable {
		@FXML
		// Which one I am.
		private final int id;
		// The chopsticks on either side of me.
		private final Chopstick leftChopStick;
		private final Chopstick rightChopStick;
		// Am I full?
		volatile boolean isTummyFull = false;
		// To randomize eat/Think time
		private Random randomGenerator = new Random();
		// Number of times I was able to eat.
		private int noOfTurnsToEat = 0;
		public Dine dine = new Dine();

		/**
		 * **
		 *
		 * @param id             Philosopher number
		 *
		 * @param leftChopStick
		 * @param rightChopStick
		 */
		public Philosopher(int id, Chopstick leftChopStick, Chopstick rightChopStick) {
			this.id = id;
			this.leftChopStick = leftChopStick;
			this.rightChopStick = rightChopStick;
		}

		@Override
		public void run() {

			try {
				while (!isTummyFull) {
					// Think for a bit.
					think();

					// HUNGRY
					// Make the mechanism obvious.
					if (leftChopStick.pickUp(this, "left")) {
						if (rightChopStick.pickUp(this, "right")) {
							// Eat some.
							// dine.moveSticks();
							eat();
							// Finished.
							rightChopStick.putDown(this, "right");
						}
						// Finished.
						leftChopStick.putDown(this, "left");
					}
				}
			} catch (Exception e) {
				// Catch the exception outside the loop.
				e.printStackTrace();
			}
		}

		private void think() throws InterruptedException {
			System.out.println(this + " is thinking");
			Thread.sleep(randomGenerator.nextInt(1000));

		}

		private void eat() throws InterruptedException {
			System.out.println(this + " is eating");
			noOfTurnsToEat++;
			Thread.sleep(randomGenerator.nextInt(10000));

		}

		// Accessors at the end.
		public int getNoOfTurnsToEat() {
			return noOfTurnsToEat;
		}

		@Override
		public String toString() {
			return "Philosopher-" + id;
		}
	}

	/* CHOPSTICKS CLASS */

	public class Chopstick {
		@FXML
		private Line stick1;
		// Make sure only one philosopher can have me at any time.
		Lock up = new ReentrantLock(true);
		// Who I am.
		private final int id;

		public Chopstick() {
			this.id = 0;
		}
		public Chopstick(int id) {
			this.id = id;
		}

		public boolean pickUp(Philosopher who, String where) throws InterruptedException {
			if (up.tryLock(10000, TimeUnit.MILLISECONDS)) {
				moveSticks();

				System.out.println(who + " picked up " + where + " " + this);
				return true;
			}
			return false;
		}

		public void putDown(Philosopher who, String name) {
			up.unlock();
			System.out.println(who + " put down " + name + " " + this);
		}
		
		public void moveSticks() {
			// Create a Runnable
			Runnable task = new Runnable() {
				public void run() {
					chop1();
				}
			};

			// Run the task in a background thread
			Thread backgroundThread = new Thread(task);
			// Terminate the running thread if the application exits
			backgroundThread.setDaemon(true);
			// Start the thread
			backgroundThread.start();
		}
		
		public void chop1() {
{
				// Update the Label on the JavaFx Application Thread
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						TranslateTransition translate = new TranslateTransition();
						translate.setNode(stick1);
						translate.setDuration(Duration.millis(1000));
						translate.setCycleCount(2);
						translate.setByX(-70);
						//translate.setAutoReverse(true);
						translate.play();
						System.out.println("moving a chopstick");

					}
				});
			}
		}

		@Override
		public String toString() {
			return "Chopstick-" + id;
		}

		public int getId() {
			return id;
		}
	}

	@FXML
	private Circle myCicle;
	@FXML
	private Line stick1, stick2, stick3, stick4, stick5;
	@FXML
	private Circle person1, person2, person3, person4, person5;
	@FXML
	private Label label = new Label();
	@FXML
	private Button exitButton = new Button("Exit");
	@FXML 
	private Button simulateButton = new Button("Simulate");
	
	private static Stage stage;
	public DiningPhilosopherProblem diner = new DiningPhilosopherProblem();
	public Chopstick chopchop = new Chopstick();
	
//	private TextArea textAreaUI;

//	static TextArea staticTxtArea;

	@Override
	public void start(final Stage primaryStage) {
		try {
			// Parent root = FXMLLoader.load(getClass().getResource("Scene.fxml"));

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("test.fxml"));
			Parent root = loader.load();
			stage = primaryStage;
			Scene scene = new Scene(root);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

//			exitButton2.setOnAction(new EventHandler <ActionEvent>() {
//				
//				public void handle(ActionEvent e) {
//					// TODO Auto-generated method stub
//					primaryStage.close();
//				}
//			});
			// Create the ButtonBox
			// HBox buttonBox = new HBox(5, startButton, exitButton2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void initialize(URL location, ResourceBundle resources) {

//		staticTxtArea = textAreaUI;

		person1.setStroke(Color.SEAGREEN);
		Image p1Img = new Image(getClass().getResourceAsStream("icon.png"));
		person1.setFill(new ImagePattern(p1Img));
		person1.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

		person2.setStroke(Color.SEAGREEN);
		Image p2Img = new Image(getClass().getResourceAsStream("icon.png"));
		person2.setFill(new ImagePattern(p2Img));
		person2.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

		person3.setStroke(Color.SEAGREEN);
		Image p3Img = new Image(getClass().getResourceAsStream("icon.png"));
		person3.setFill(new ImagePattern(p3Img));
		person3.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

		person4.setStroke(Color.SEAGREEN);
		Image p4Img = new Image(getClass().getResourceAsStream("icon.png"));
		person4.setFill(new ImagePattern(p4Img));
		person4.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

		person5.setStroke(Color.SEAGREEN);
		Image p5Img = new Image(getClass().getResourceAsStream("icon.png"));
		person5.setFill(new ImagePattern(p5Img));
		person5.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

	}


	public void simulate() throws InterruptedException {
		chopchop.moveSticks();
		dining();
		label.setText(diner.s);
	}
	
	public void dining() {
		// Create a Runnable
		Runnable task = new Runnable() {
			public void run() {
				try {
					diner.dine(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		// Run the task in a background thread
		Thread backgroundThread = new Thread(task);
		// Terminate the running thread if the application exits
		backgroundThread.setDaemon(true);
		// Start the thread
		backgroundThread.start();
	}


	public void exit(ActionEvent event) {
		stage.close();
	}


	public void animate() {
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(stick1);
		translate.setDuration(Duration.millis(1000));
		translate.setCycleCount(2);
		translate.setByX(-70);
		translate.setAutoReverse(true);
		translate.play();

		TranslateTransition translate1 = new TranslateTransition();
		translate1.setNode(stick2);
		translate1.setDuration(Duration.millis(1000));
		translate1.setCycleCount(3);
		translate1.setByX(-50);
		translate1.setByY(-20);
		translate1.setAutoReverse(true);
		translate1.play();

		TranslateTransition translate2 = new TranslateTransition();
		translate2.setNode(stick3);
		translate2.setDuration(Duration.millis(1000));
		translate2.setDelay(Duration.millis(2000));
		translate2.setByX(20);
		translate2.setByY(-40);
		translate2.play();

		TranslateTransition translate3 = new TranslateTransition();
		translate3.setNode(stick4);
		translate3.setDuration(Duration.millis(1000));
		translate3.setDelay(Duration.millis(3000));
		translate3.setByX(70);
		translate3.play();

		TranslateTransition translate4 = new TranslateTransition();
		translate4.setNode(stick5);
		translate4.setDuration(Duration.millis(1000));
		translate4.setDelay(Duration.millis(3000));
		translate4.setByX(20);
		translate4.setByY(70);
		translate4.play();
	}

}
