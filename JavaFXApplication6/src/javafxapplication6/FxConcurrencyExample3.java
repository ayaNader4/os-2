package javafxapplication6;

import java.io.IOException;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FxConcurrencyExample3 extends Application {
	
	public class Thread1 {
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
			for (int i = 1; i <= 10; i++) {
				try {
					// Update the Label on the JavaFx Application Thread
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
							System.out.println("moving chop stick");

						}
					});

					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public class Thread2 {
		public Thread1 th = new Thread1();

		public void printOut() {
			// Create a Runnable
			Runnable task = new Runnable() {
				public void run() {
					spam();
				}
			};

			// Run the task in a background thread
			Thread backgroundThread = new Thread(task);
			// Terminate the running thread if the application exits
			backgroundThread.setDaemon(true);
			// Start the thread
			backgroundThread.start();
		}

		public void spam() {
			for (int i = 1; i <= 10; i++) {
				try {
					// Get the Status
					final String status = "Processing " + i + " of " + 10;

					// Update the Label on the JavaFx Application Thread
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							th.moveSticks();
							System.out.println("spam");

						}
					});

					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
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

	private Thread2 thread = new Thread2();

	private static Stage stage;

	Button startButton = new Button("Start");

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(final Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage = primaryStage;

		// Add the scene to the Stage
		primaryStage.setScene(scene);
		// Set the title of the Stage
		primaryStage.setTitle("A simple Concurrency Example");
		// Display the Stage
		primaryStage.show();
	}

	public void simulate() throws InterruptedException {
		thread.printOut();

	}

	public void exit(ActionEvent event) {
		stage.close();
	}

}
