/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import londoncut.Main;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane home;
   
    @FXML
    private Label labelclose;
    @FXML
    private Label labelminimize;
    
    private static double xOffset = 0;
    private static double yOffset = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image imageRed = new Image("londoncut/images/red.png");
        Image imageGreen = new Image("londoncut/images/green.png");
        labelclose.setGraphic(new ImageView(imageRed));
        labelminimize.setGraphic(new ImageView(imageGreen));
        try {
            AnchorPane pane = FXMLLoader.load(Main.class.getResource("views/homeOptions.fxml"));//homeOptions.fxml
            root.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void closePopDown(MouseEvent event) {
        popDown(event, labelclose);
    }

    @FXML
    private void closePopUp(MouseEvent event) {
        popUp(event, labelclose);
    }

    @FXML
    private void minimizePopDown(MouseEvent event) {
        popDown(event, labelminimize);
    }

    @FXML
    private void minimizePopUp(MouseEvent event) {
        popUp(event, labelminimize);
    }

    private void popUp(MouseEvent event,Label label){
        if(event.getSource() instanceof Label){
            label=(Label)event.getSource();
            ScaleTransition st=new ScaleTransition(Duration.millis(200),label);
            st.setToX(1.2);
            st.setToY(1.2);
            st.play();
            
            DropShadow glow=new DropShadow();
            glow.setColor(Color.BLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(5);
            label.setEffect(glow);
        }
    }
    
    private void popDown(MouseEvent event,Label label){
        if(event.getSource() instanceof Label){
            label=(Label)event.getSource();
            ScaleTransition st=new ScaleTransition(Duration.millis(200),label);
            st.setToX(1);
            st.setToY(1);
            st.play();
            
            label.setEffect(null);
        }
    }

    @FXML
    private void minimizeWindow(MouseEvent event) {
        Stage stage=(Stage)home.getScene().getWindow();
        stage=(Stage)((Label)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void windowClose(MouseEvent event) {
        Stage stage=(Stage)home.getScene().getWindow();
        stage=(Stage)((Label)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void stayDown(MouseEvent event) {
        popDown(event, labelclose);
        popDown(event, labelminimize);
    }

    @FXML
    private void setOnMousePress(MouseEvent event) {
        xOffset=event.getSceneX();
        yOffset=event.getSceneY();
    }

    @FXML
    private void moveWindow(MouseEvent event) {
        Stage stage=(Stage)home.getScene().getWindow();
        stage.setX(event.getScreenX()-xOffset);
        stage.setY(event.getScreenY()-yOffset);
    }

    
}
