/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import londoncut.alerts.MessageAlert;
import londoncut.events.TextKeyHandling;
import londoncut.models.Customer;
import londoncut.models.controller.CustomerController;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class AddcustomerdetailController implements Initializable {

    @FXML
    private JFXTextField txtfname;
    @FXML
    private JFXTextField txtlname;
    @FXML
    private JFXTextField txtcontact;
    @FXML
    private JFXTextField txtnic;
    @FXML
    private JFXButton btnupdatecustomer;
    @FXML
    private TextField txtcustomerid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list=new UpdatecustomerController().setDatatoFields();
        txtcustomerid.setText(list.get(0));
        txtfname.setText(list.get(1));
        txtlname.setText(list.get(2));
        txtcontact.setText(list.get(3));
        txtnic.setText(list.get(4));
    }    

    @FXML
    private void updateCustomer(ActionEvent event) {
        new CustomerController().updateCustomer(new Customer( 
                txtcustomerid.getText(), 
                txtfname.getText(), 
                txtlname.getText(), 
                Integer.parseInt(txtcontact.getText()), 
                txtnic.getText()));
        MessageAlert.ShowMessage("Customer Updated...", "Done", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void characterOnly(KeyEvent event) {
        TextKeyHandling.onlyCharacters(event);
    }

    @FXML
    private void numbersOnly(KeyEvent event) {
        TextKeyHandling.onlyNumbers(event);
    }
    
}
