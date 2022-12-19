package javafxapplication6;


import java.util.Random;
import javafx.fxml.FXML;

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
				
				//HUNGRY
				// Make the mechanism obvious.
				if (leftChopStick.pickUp(this, "left")) {
					if (rightChopStick.pickUp(this, "right")) {
						// Eat some.
						//dine.moveSticks();
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