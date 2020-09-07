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
public class HomeOptionsController implements Initializable {

    @FXML
    private AnchorPane optionPane;
    @FXML
    private JFXButton btnmeasurement;
    @FXML
    private JFXButton btncustomer;
    @FXML
    private JFXButton btnworkorder;
    @FXML
    private JFXButton btncoat;
    @FXML
    private JFXButton btnproduct;
    @FXML
    private JFXButton btnmaterial;
    @FXML
    private JFXButton btntailor;
    @FXML
    private JFXButton btnpayment;

    
    
    @FXML
    public void nextMeasurementPage(ActionEvent event) throws IOException {
       updatePane("views/measurement_2.fxml");
        
    }
    
    @FXML
    public void nextCustomerPage(ActionEvent event) throws IOException {
        updatePane("views/customer_2.fxml");
    }
    
    @FXML
    public void nextWorkOrderPage(ActionEvent event) throws IOException {
        updatePane("views/workorder_2.fxml");
        
    }
    
    @FXML
    private void nextCoatPage(ActionEvent event) throws IOException {
        updatePane("views/coat_2.fxml");
    }
    
    @FXML
    private void nextProductPage(ActionEvent event) throws IOException {
        updatePane("views/product_2.fxml");
    }
    
    @FXML
    private void nextMaterialPage(ActionEvent event) throws IOException {
        updatePane("views/material_2.fxml");
    }
    
     @FXML
    private void nextTailorPage(ActionEvent event) throws IOException {
         updatePane("views/tailor_2.fxml");
    }
    
    private void loadtailorpayment(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(Main.class.getResource("views/tailorpayment.fxml"));
        Scene scene=new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void updatePane(String name) throws IOException{
        AnchorPane pane=FXMLLoader.load(Main.class.getResource(name));
        optionPane.getChildren().clear();
        optionPane.getChildren().add(pane);
        FadeTransition ft=new FadeTransition();
        ft.setDuration(Duration.millis(1000));
        ft.setNode(pane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition ft=new FadeTransition();
        ft.setDuration(Duration.millis(1000));
        ft.setNode(optionPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        
        
        
    }    

    @FXML
    private void loadPayment_2(ActionEvent event) throws IOException {
        updatePane("views/payment_2.fxml");
        
    }
    
    

    

   

    

    

    
   
    
    

  
    
}
