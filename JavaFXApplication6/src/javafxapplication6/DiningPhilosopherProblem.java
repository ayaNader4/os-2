/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package javafxapplication6;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author nader
 */
public class DiningPhilosopherProblem extends Application implements Initializable {


	private static int NO_OF_PHILOSOPHER;
	// private static final int SIMULATION_MILLIS = 100;
	private static final int SIMULATION_MILLIS = 1 * 5 * 1000;
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
