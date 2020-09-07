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
public class ManageproductController implements Initializable {

    @FXML
    private AnchorPane manageproductPane;
    @FXML
    private JFXButton btnaddproduct;
    @FXML
    private JFXButton btndeleteproduct;
    @FXML
    private JFXButton btnupdateproduct;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXButton btnhome;

    
    @FXML
    public void loadaddProduct(ActionEvent event) throws IOException {
        updatePane("views/addproduct.fxml");
    }
    
      @FXML
    private void loaddeleteproduct(ActionEvent event) throws IOException {
          updatePane("views/deleteproduct.fxml");
    }
    
    @FXML
    private void loadupdateproduct(ActionEvent event) throws IOException {
        updatePane("views/updateproduct.fxml");
    }
    
    @FXML
    private void loadHome(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
        Scene scene=new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    public void updatePane(String name) throws IOException{
        AnchorPane pane=FXMLLoader.load(Main.class.getResource(name));
        rootPane.getChildren().clear();
        rootPane.getChildren().add(pane);
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition ft=new FadeTransition();
        ft.setDuration(Duration.millis(1000));
        ft.setNode(manageproductPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        try {
            
            AnchorPane pane=FXMLLoader.load(Main.class.getResource("views/addproduct.fxml"));;
            rootPane.getChildren().add(pane);
            
            
        } catch (IOException ex) {
            Logger.getLogger(ManageproductController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }    

    

    

  
    
}
