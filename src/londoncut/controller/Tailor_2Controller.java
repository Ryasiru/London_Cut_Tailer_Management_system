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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import londoncut.Main;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class Tailor_2Controller implements Initializable {

    @FXML
    private AnchorPane selecttailorPane;
    @FXML
    private JFXButton btnback;
    @FXML
    private JFXButton btnmanagetailor;
    @FXML
    private JFXButton btntailorwork;

     @FXML
    private void backHome(ActionEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(Main.class.getResource("views/homeOptions.fxml"));
        selecttailorPane.getChildren().clear();
        selecttailorPane.getChildren().add(pane);
    }
    
    @FXML
    private void loadmanagetailor(ActionEvent event) throws IOException {
        loadWindow(event,"views/managetailor.fxml");
                
    }
    
    @FXML
    private void loadtailorwork(ActionEvent event) throws IOException {
        loadWindow(event,"views/tailorwork.fxml");
    }
    
    private void loadWindow(ActionEvent event,String name) throws IOException{
        Parent root=FXMLLoader.load(Main.class.getResource(name));
        Scene scene=new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    

    

   
}
