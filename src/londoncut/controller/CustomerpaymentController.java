/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import londoncut.Main;
import londoncut.alerts.MessageAlert;
import londoncut.models.controller.OrdersController;
import londoncut.tables.controller.tblCustomerPaymentController;
import londoncut.tables.tblCustomerPayment;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class CustomerpaymentController implements Initializable {

    @FXML
    private AnchorPane customerpaymentPane;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnhome;
    @FXML
    private JFXButton btnnext;
    @FXML
    private JFXTextField txtsearch;
    @FXML
    private TableView<tblCustomerPayment> tblorderdetail;
    @FXML
    private TableColumn<tblCustomerPayment, String> colOrdernumber;
    @FXML
    private TableColumn<tblCustomerPayment, String> colCustomer;
    @FXML
    private TableColumn<tblCustomerPayment, String> colFname;
    @FXML
    private TableColumn<tblCustomerPayment, String> colLname;
    @FXML
    private TableColumn<tblCustomerPayment, String> colPaymentdate;
    
    @FXML
    private void loadcustomerpayment2(ActionEvent event) throws IOException {
       if(tblorderdetail.getSelectionModel().isEmpty()){
           MessageAlert.ShowMessage("No Order is Selected", "Error", Alert.AlertType.ERROR);
       }else{
            new OrdersController().getSelectedOrder(tblorderdetail.getSelectionModel().getSelectedItem().getOrdernumber());
            Parent next=FXMLLoader.load(Main.class.getResource("views/customerpayment_2.fxml"));
            Scene scene=btnnext.getScene();
            next.translateXProperty().set(scene.getWidth());
            root.getChildren().add(next);

            Timeline timeline=new Timeline();
            KeyValue kv=new KeyValue(next.translateXProperty(),0,Interpolator.EASE_IN);
            KeyFrame kf=new KeyFrame(Duration.seconds(1),kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(Doevent->{
                root.getChildren().remove(root);
            });
            timeline.play();
       }
       

    }
    
    @FXML
    private void backHome(ActionEvent event) throws IOException {
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
        ft.setNode(customerpaymentPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        
        initiateTable();
        tblorderdetail.setItems(new tblCustomerPaymentController().loadTable());
        
        
    }    

    private void initiateTable(){
        colOrdernumber.setCellValueFactory(new PropertyValueFactory<>("ordernumber"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customerid"));
        colFname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colLname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colPaymentdate.setCellValueFactory(new PropertyValueFactory<>("paymentdate"));
    }

    @FXML
    private void autoLoadData(KeyEvent event) throws SQLException {
        tblorderdetail.setItems(new tblCustomerPaymentController().autoLoad(txtsearch.getText()));
    }

    

    
    
}
