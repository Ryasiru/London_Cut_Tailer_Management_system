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
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import londoncut.Main;
//import sun.plugin.javascript.navig.Anchor;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class ManagetailorController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnaddtailor;
    @FXML
    private JFXButton btndeletetailor;
    @FXML
    private JFXButton btnupdatetailor;
    @FXML
    private JFXButton btnhome;
    @FXML
    private AnchorPane managetailorPane;

    @FXML
    private void loadaddtailor(ActionEvent event) throws IOException {
        loadPane("views/addtailor.fxml");
    }

    @FXML
    private void loaddeletetailor(ActionEvent event) throws IOException {
        loadPane("views/deletetailor.fxml");
    }

    @FXML
    private void loadupdatetailor(ActionEvent event) throws IOException {
        loadPane("views/updatetailor.fxml");
    }

    @FXML
    private void loadhome(ActionEvent event) throws IOException {
        Parent home=FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
        Scene scene=new Scene(home);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    
    
    private void loadPane(String name) throws IOException{
        AnchorPane pane=FXMLLoader.load(Main.class.getResource(name));
        root.getChildren().clear();
        root.getChildren().add(pane);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        FadeTransition ft=new FadeTransition();
        ft.setDuration(Duration.millis(1000));
        ft.setNode(managetailorPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        
        try {
            loadPane("views/addtailor.fxml");
        } catch (IOException ex) {
            Logger.getLogger(ManagetailorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    
    
}
