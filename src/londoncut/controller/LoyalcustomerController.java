/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import londoncut.Main;
import londoncut.alerts.MessageAlert;
import londoncut.models.controller.CustomerController;
import londoncut.tables.controller.tblLoyalCustomerController;
import londoncut.tables.tblLoyalCustomer;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class LoyalcustomerController implements Initializable {

    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnnext;
    @FXML
    private AnchorPane loyalcustomerPane;
    @FXML
    private TableView<tblLoyalCustomer> tblloyalcustomer;
    @FXML
    private TableColumn<tblLoyalCustomer, String> colCustomer;
    @FXML
    private TableColumn<tblLoyalCustomer, String> colFname;
    @FXML
    private TableColumn<tblLoyalCustomer, String> colLname;
    @FXML
    private TableColumn<tblLoyalCustomer, String> colNIC;
    @FXML
    private TableColumn<tblLoyalCustomer, Integer> colContact;
    @FXML
    private JFXTextField txtsearch;

    @FXML
    private void loadmeasurement(ActionEvent event) throws IOException {
       if(tblloyalcustomer.getSelectionModel().isEmpty()){
           MessageAlert.ShowMessage("No Customer Selected", "Error", Alert.AlertType.ERROR);
       }else{
            Parent root=FXMLLoader.load(Main.class.getResource("views/measurement_3.fxml"));
            Scene scene=btnnext.getScene();
            root.translateXProperty().set(scene.getWidth());
            loyalcustomerPane.getChildren().add(root);

            Timeline timeline=new Timeline();
            KeyValue kv=new KeyValue(root.translateXProperty(),0,Interpolator.EASE_IN);
            KeyFrame kf=new KeyFrame(Duration.seconds(1),kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(Doevent->{
                loyalcustomerPane.getChildren().remove(loyalcustomerPane);
            });
            timeline.play(); 
       }
       
    }
    
    @FXML
    private void backhome(ActionEvent event) throws IOException {
        Parent next=FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
        Scene scene=new Scene(next);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTable();
        tblloyalcustomer.setItems(new tblLoyalCustomerController().loadTable());
        
    }    

    private void initiateTable(){
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customerid"));
        colFname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colLname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
    }

    @FXML
    private void getCustomerid(MouseEvent event) {
        
        new CustomerController().getSelectedCustomerid(tblloyalcustomer.getSelectionModel()
        .getSelectedItem().getCustomerid());
    }

    @FXML
    private void autoSearch(KeyEvent event) {
        tblloyalcustomer.setItems(new tblLoyalCustomerController().autoLoad(txtsearch.getText()));
    }

 
    
}
