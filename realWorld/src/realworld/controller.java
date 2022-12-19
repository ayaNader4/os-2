/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package realworld;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;


/**
 *
 * @author nader
 */
public class controller implements Initializable{
    @FXML
    private Circle myCicle;
    @FXML
    private Line stick1,stick2,stick3,stick4,stick5;
    @FXML
    private Circle person1,person2,person3,person4,person5;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TranslateTransition translate= new TranslateTransition();
        translate.setNode(stick1);
        translate.setDuration(Duration.millis(1000));
        translate.setCycleCount(2);
        translate.setByX(-70);
        translate.setAutoReverse(true);
        translate.play();
        
        TranslateTransition translate1= new TranslateTransition();
        translate1.setNode(stick2);
        translate1.setDuration(Duration.millis(1000));
        translate1.setCycleCount(3);
        translate1.setByX(-50);
        translate1.setByY(-20);
        translate1.setAutoReverse(true);
        translate1.play();
        
        TranslateTransition translate2= new TranslateTransition();
        translate2.setNode(stick3);
        translate2.setDuration(Duration.millis(1000));
        translate2.setDelay(Duration.millis(2000));
        translate2.setByX(20);
        translate2.setByY(-40);
        translate2.play(); 
        
        TranslateTransition translate3= new TranslateTransition();
        translate3.setNode(stick4);
        translate3.setDuration(Duration.millis(1000));
        translate3.setDelay(Duration.millis(3000));
        translate3.setByX(70);
        translate3.play(); 
        
        TranslateTransition translate4= new TranslateTransition();
        translate4.setNode(stick5);
        translate4.setDuration(Duration.millis(1000));
        translate4.setDelay(Duration.millis(3000));
        translate4.setByX(20);
        translate4.setByY(70);
        translate4.play(); 
        
        person1.setStroke(Color.SEAGREEN);
        Image p1Img = new Image(getClass().getResourceAsStream("man.png"));
        person1.setFill(new ImagePattern(p1Img));
        person1.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
        
        person2.setStroke(Color.SEAGREEN);
        Image p2Img = new Image(getClass().getResourceAsStream("avatar.png"));
        person2.setFill(new ImagePattern(p2Img));
        person2.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
        
        person3.setStroke(Color.SEAGREEN);
        Image p3Img = new Image(getClass().getResourceAsStream("woman.png"));
        person3.setFill(new ImagePattern(p3Img));
        person3.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
        
        person4.setStroke(Color.SEAGREEN);
        Image p4Img = new Image(getClass().getResourceAsStream("man (1).png"));
        person4.setFill(new ImagePattern(p4Img));
        person4.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
        
        person5.setStroke(Color.SEAGREEN);
        Image p5Img = new Image(getClass().getResourceAsStream("10.jpg"));
        person5.setFill(new ImagePattern(p5Img));
        person5.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
    }

   
    
}
