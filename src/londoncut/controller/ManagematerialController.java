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

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class ManagematerialController implements Initializable {

    @FXML
    private AnchorPane managematerialPane;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnaddmaterial;
    @FXML
    private JFXButton btndeletematerial;
    @FXML
    private JFXButton btnupdatematerial;
    @FXML
    private JFXButton btnhome;

    
    @FXML
    private void loadaddmaterial(ActionEvent event) throws IOException {
        loadPane("views/addmaterial.fxml");
    }
    
    @FXML
    private void loaddeletematerial(ActionEvent event) throws IOException {
        loadPane("views/deletematerial.fxml");
    }
    
    @FXML
    private void loadupdatematerial(ActionEvent event) throws IOException {
        loadPane("views/updatematerial.fxml");
    }
    
    private void loadPane(String name) throws IOException{
        AnchorPane pane=FXMLLoader.load(Main.class.getResource(name));
        root.getChildren().clear();
        root.getChildren().add(pane);
    }
    
    @FXML
    private void loadhome(ActionEvent event) throws IOException {
        Parent home=FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
        Scene scene=new Scene(home);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition ft=new FadeTransition();
        ft.setDuration(Duration.millis(1000));
        ft.setNode(managematerialPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        
        try {
            
            AnchorPane pane=FXMLLoader.load(Main.class.getResource("views/addmaterial.fxml"));
            root.getChildren().clear();
            root.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(ManagematerialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    
}
