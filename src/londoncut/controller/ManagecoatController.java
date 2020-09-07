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
public class ManagecoatController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnaddcoat;
    @FXML
    private AnchorPane managecoatPane;
    @FXML
    private JFXButton btndeletecoat;
    @FXML
    private JFXButton btnupdatecoat;
    @FXML
    private JFXButton btnhome;

    
    @FXML
    private void loadaddcoat(ActionEvent event) throws IOException {
        loadPane("views/addcoat.fxml");
    }
    
    @FXML
    private void loaddeletecoat(ActionEvent event) throws IOException {
        loadPane("views/deletecoat.fxml");
    }
    
    @FXML
    private void loadupdatecoat(ActionEvent event) throws IOException {
        loadPane("views/updatecoat.fxml");
    }
    
    public void loadPane(String name) throws IOException{
        AnchorPane pane=FXMLLoader.load(Main.class.getResource(name));
        root.getChildren().clear();
        root.getChildren().add(pane);
    }
    
    @FXML
    private void loadHome(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
        Scene scene=new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadPane("views/addcoat.fxml");
            FadeTransition ft=new FadeTransition();
            ft.setDuration(Duration.millis(1000));
            ft.setNode(managecoatPane);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();
            
        } catch (IOException ex) {
            Logger.getLogger(ManagecoatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    

    

    

    
}
