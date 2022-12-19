import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RealWorldProblem {
	// How many to test with.
		private static int NO_OF_COMPUTERS;
		// private static final int SIMULATION_MILLIS = 100;
		private static final int SIMULATION_MILLIS = 1 * 60 * 1000;

		public static void main(String args[]) throws InterruptedException {
			ExecutorService executorService = null;
			
			try (Scanner scan = new Scanner(System.in)) {
				System.out.println("Enter the number of Computers");
				NO_OF_COMPUTERS = scan.nextInt();
			}
			Computer[] computers = null;
			try {

				computers = new Computer[NO_OF_COMPUTERS];

				// As many wires as Computers
				Wire[] wires = new Wire[NO_OF_COMPUTERS];
				// Cannot do this as it will fill the whole array with the SAME Wire.
				// Arrays.fill(Wires, new ReentrantLock());
				for (int i = 0; i < NO_OF_COMPUTERS; i++) {
					wires[i] = new Wire(i);
				}

				executorService = Executors.newFixedThreadPool(NO_OF_COMPUTERS);

				for (int i = 0; i < NO_OF_COMPUTERS; i++) {
					computers[i] = new Computer(i, wires[i], wires[(i + 1) % NO_OF_COMPUTERS]);
					executorService.execute(computers[i]);
				}
				// Main thread sleeps till time of simulation
				Thread.sleep(SIMULATION_MILLIS);
				// Stop all computers.
				for (Computer computer : computers) {
					computer.isConnected = true;
				
				}

			} finally {
				// Close everything down.
				executorService.shutdown();
				// Wait for all thread to finish
				while (!executorService.isTerminated()) {
					Thread.sleep(10000);
				}

				// Time for check
				for (Computer computer : computers) {
					System.out.println(computer + " => No of Turns to play =" + computer.getnoOfTurnsToPlay());
				}
			}
		}
	}