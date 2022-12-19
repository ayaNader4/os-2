/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxapplication6;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author nader
 */
public class FXMLDocumentController implements Initializable {

	@FXML
	private Circle myCicle;
	@FXML
	private Line stick1, stick2, stick3, stick4, stick5;
	@FXML
	private Circle person1, person2, person3, person4, person5;
	@FXML
	private Label label = new Label();
	@FXML
	private Button exitButton;
	
	public DiningPhilosopherProblem diner = new DiningPhilosopherProblem();
	@FXML
	private void handleButtonAction(ActionEvent event) {
		System.out.println("You clicked me!");
		label.setText("Hello World!");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

}
