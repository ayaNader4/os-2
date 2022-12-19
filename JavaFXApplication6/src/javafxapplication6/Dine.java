package javafxapplication6;

import java.net.URL;
import java.util.ResourceBundle;

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

	private static Stage stage;
	public DiningPhilosopherProblem diner = new DiningPhilosopherProblem();
//	private TextArea textAreaUI;

//	static TextArea staticTxtArea;

	@Override
	public void start(final Stage primaryStage) {
		try {
			// Parent root = FXMLLoader.load(getClass().getResource("Scene.fxml"));

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
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

//		Runnable transitionDrawer = () -> {
//			TranslateTransition translate = new TranslateTransition();
//			translate.setNode(stick1);
//			translate.setDuration(Duration.millis(1000));
//			translate.setCycleCount(2);
//			translate.setByX(-70);
//			translate.setAutoReverse(true);
//			translate.play();
//
//			TranslateTransition translate1 = new TranslateTransition();
//			translate1.setNode(stick2);
//			translate1.setDuration(Duration.millis(1000));
//			translate1.setCycleCount(3);
//			translate1.setByX(-50);
//			translate1.setByY(-20);
//			translate1.setAutoReverse(true);
//			translate1.play();
//
//			TranslateTransition translate2 = new TranslateTransition();
//			translate2.setNode(stick3);
//			translate2.setDuration(Duration.millis(1000));
//			translate2.setDelay(Duration.millis(2000));
//			translate2.setByX(20);
//			translate2.setByY(-40);
//			translate2.play();
//
//			TranslateTransition translate3 = new TranslateTransition();
//			translate3.setNode(stick4);
//			translate3.setDuration(Duration.millis(1000));
//			translate3.setDelay(Duration.millis(3000));
//			translate3.setByX(70);
//			translate3.play();
//
//			TranslateTransition translate4 = new TranslateTransition();
//			translate4.setNode(stick5);
//			translate4.setDuration(Duration.millis(1000));
//			translate4.setDelay(Duration.millis(3000));
//			translate4.setByX(20);
//			translate4.setByY(70);
//			translate4.play();
//		};
//		Thread tran = new Thread(transitionDrawer);
//		tran.start();
	}
	
	public void moveSticks() {
		Runnable task = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("moving");
				chop1();
			}};
			
			// Run the task in a background thread
			Thread backgroundThread = new Thread(task);
			// Terminate the running thread if the application exits
			backgroundThread.setDaemon(true);
			// Start the thread
			backgroundThread.start();
	}
	
	
	public void simulate() throws InterruptedException {
		
	
		Runnable simulator = () -> {
			try {
				diner.dine(5);
				moveSticks();
				//chop1();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		};
		Thread sim = new Thread(simulator);
		sim.start();
		label.setText(diner.getOutput());

	}
	
	

//	public void handle(ActionEvent event) {
//		stage.close();
//	}

	public void exit(ActionEvent event) {
		stage.close();
	}

	public void chop1() {
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {

				TranslateTransition translate = new TranslateTransition();
				translate.setNode(stick1);
				translate.setDuration(Duration.millis(1000));
				translate.setCycleCount(2);
				translate.setByX(-70);
				translate.setAutoReverse(true);
				translate.play();
				System.out.println("chopping");
				
			}
		});
		

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
//	public void taken(int id) {
//
//		if (id == 0) {
//
//			TranslateTransition translate = new TranslateTransition();
//			translate.setNode(stick1);
//			translate.setDuration(Duration.millis(1000));
//			translate.setCycleCount(2);
//			translate.setByX(-70);
//			translate.setAutoReverse(true);
//			translate.play();
//
//		} else if (id == 1) {
//			TranslateTransition translate1 = new TranslateTransition();
//			translate1.setNode(stick2);
//			translate1.setDuration(Duration.millis(1000));
//			translate1.setCycleCount(3);
//			translate1.setByX(-50);
//			translate1.setByY(-20);
//			translate1.setAutoReverse(true);
//			translate1.play();
//		} else if (id == 2) {
//			TranslateTransition translate2 = new TranslateTransition();
//			translate2.setNode(stick3);
//			translate2.setDuration(Duration.millis(1000));
//			translate2.setDelay(Duration.millis(2000));
//			translate2.setByX(20);
//			translate2.setByY(-40);
//			translate2.play();
//		} else if (id == 3) {
//			TranslateTransition translate3 = new TranslateTransition();
//			translate3.setNode(stick4);
//			translate3.setDuration(Duration.millis(1000));
//			translate3.setDelay(Duration.millis(3000));
//			translate3.setByX(70);
//			translate3.play();
//		} else if (id == 4) {
//			TranslateTransition translate4 = new TranslateTransition();
//			translate4.setNode(stick5);
//			translate4.setDuration(Duration.millis(1000));
//			translate4.setDelay(Duration.millis(3000));
//			translate4.setByX(20);
//			translate4.setByY(70);
//			translate4.play();
//		}
//
//	}

}
