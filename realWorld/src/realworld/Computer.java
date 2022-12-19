import java.util.Random;

public class Computer  implements Runnable {
	// Which one I am.
	private final int id;
	// The Wire on either side of me.
	private final Wire leftWire;
	private final Wire rightWire;
	// Am I full?
	volatile boolean isConnected = false;
	// To randomize play/rest time
	private Random randomGenerator = new Random();
	// Number of times I was able to play.
	private int noOfTurnsToPlay = 0;

	/**
	 * **
	 *
	 * @param id             Computer number
	 *
	 * @param leftWire
	 * @param rightWire
	 */
	public Computer(int id, Wire leftWire, Wire rightWire) {
		this.id = id;
		this.leftWire = leftWire;
		this.rightWire = rightWire;
	}

	@Override
	public void run() {

		try {
			while (!isConnected) {
				// rest for a bit.
				rest();
				// Make the mechanism obvious.
				if (leftWire.pickUp(this, "left")) {
					if (rightWire.pickUp(this, "right")) {
						// play some.
						play();
						// Finished.
						rightWire.putDown(this, "right");
					}
					// Finished.
					leftWire.putDown(this, "left");
				}
			}
		} catch (Exception e) {
			// Catch the exception outside the loop.
			e.printStackTrace();
		}
	}

	private void rest() throws InterruptedException {
		System.out.println(this + " is resting");
		Thread.sleep(randomGenerator.nextInt(1000));
	}

	private void play() throws InterruptedException {
		System.out.println(this + " is playing");
		noOfTurnsToPlay++;
		Thread.sleep(randomGenerator.nextInt(1000));
	}

	// Accessors at the end.
	public int getnoOfTurnsToPlay() {
		return noOfTurnsToPlay;
	}

	@Override
	public String toString() {
		return "Computer-" + id;
	}
}
