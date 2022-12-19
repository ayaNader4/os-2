package testingConsole;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafxapplication6.DiningPhilosopherProblem;

public class MainController {
	@FXML
	private TextArea console;
	private PrintStream ps;
	public DiningPhilosopherProblem diner = new DiningPhilosopherProblem();

	public void initialize() {
		ps = new PrintStream(new Console(console));
	}

	public void button(ActionEvent event) {
		System.setOut(ps);
		System.setErr(ps);
		try {
			diner.dine(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public class Console extends OutputStream {
		private TextArea console;

		public Console(TextArea console) {
			this.console = console;
		}

		public void appendText(String valueOf) {
			Platform.runLater(() -> console.appendText(valueOf));
		}

		public void write(int b) throws IOException {
			appendText(String.valueOf((char) b));
		}
	}
}