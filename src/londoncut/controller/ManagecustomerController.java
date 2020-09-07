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
public class ManagecustomerController implements Initializable {

    @FXML
    private AnchorPane managecustomerPane;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnadd;
    @FXML
    private JFXButton btndelete;
    @FXML
    private JFXButton btnupdate;
    @FXML
    private JFXButton btnhome;
    
     @FXML
    private void loadaddcustomer(ActionEvent event) throws IOException {
         loadPane("views/addcustomer.fxml");
    }
    
    @FXML
    private void loaddeletecustomer(ActionEvent event) throws IOException {
        loadPane("views/deletecustomer.fxml");
    }
    
    @FXML
    private void loadupdatecustomer(ActionEvent event) throws IOException {
        loadPane("views/updatecustomer.fxml");
    }
    
    @FXML
    private void backhome(ActionEvent event) throws IOException {
            Parent root=FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
            Scene scene=new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

    }
    
    public void loadPane(String name) throws IOException{
        AnchorPane pane=FXMLLoader.load(Main.class.getResource(name));
        root.getChildren().clear();
        root.getChildren().add(pane);
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FadeTransition ft=new FadeTransition();
            ft.setDuration(Duration.millis(1000));
            ft.setNode(managecustomerPane);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();
            
            
            AnchorPane pane=FXMLLoader.load(Main.class.getResource("views/addcustomer.fxml"));
            root.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(ManagecustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    

    



   
}
