import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Wire {
	// Make sure only one Computer can have me at any time.
	Lock up = new ReentrantLock(true);
	// Who I am.
	private final int id;

	public Wire(int id) {
		this.id = id;
	}

	public boolean pickUp(Computer who, String where) throws InterruptedException {
		if (up.tryLock(10000, TimeUnit.MILLISECONDS)) {
			System.out.println(who + " picked up " + where + " " + this);
			return true;
		}
		return false;
	}

	public void putDown(Computer who, String name) {
		up.unlock();
		System.out.println(who + " put down " + name + " " + this);
	}

	@Override
	public String toString() {
		return "Wire-" + id;
	}
}
