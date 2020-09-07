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
public class Payment_2Controller implements Initializable {

    @FXML
    private JFXButton btnhome;
    @FXML
    private JFXButton btnexpence;
    @FXML
    private JFXButton btntailorpayment;
    @FXML
    private JFXButton brnreport;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadHome(ActionEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(Main.class.getResource("views/homeOptions.fxml"));
        root.getChildren().clear();
        root.getChildren().add(pane);
    }

    @FXML
    private void loadTailorPayment(ActionEvent event) throws IOException {
        loadWindow(event,"views/tailorpayment.fxml");
    }
    
    private void loadWindow(ActionEvent event,String name) throws IOException{
        Parent next=FXMLLoader.load(Main.class.getResource(name));
        Scene scene=new Scene(next);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void loadExpence(ActionEvent event) throws IOException {
        loadWindow(event, "views/expence.fxml");
    }

    @FXML
    private void loadReport(ActionEvent event) throws IOException {
        loadWindow(event, "views/report.fxml");
    }
    
}
