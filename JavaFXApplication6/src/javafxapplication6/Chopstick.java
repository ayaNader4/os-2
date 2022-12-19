package javafxapplication6;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Chopstick {
	@FXML
	private Line stick1;
	// Make sure only one philosopher can have me at any time.
	Lock up = new ReentrantLock(true);
	// Who I am.
	private final int id;
	public Dine dine = new Dine();

	public Chopstick(int id) {
		this.id = id;
	}

	public boolean pickUp(Philosopher who, String where) throws InterruptedException {
		if (up.tryLock(10000, TimeUnit.MILLISECONDS)) {
			// dine.taken(this.id);
			//dine.moveSticks();

			System.out.println(who + " picked up " + where + " " + this);
			return true;
		}
		return false;
	}

	public void putDown(Philosopher who, String name) {
		up.unlock();
		System.out.println(who + " put down " + name + " " + this);
	}

	@Override
	public String toString() {
		return "Chopstick-" + id;
	}

	public int getId() {
		return id;
	}
}
