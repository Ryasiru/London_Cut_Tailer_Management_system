/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import londoncut.alerts.MessageAlert;
import londoncut.events.TextKeyHandling;
import londoncut.models.Customer;
import londoncut.models.controller.CustomerController;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class AddcustomerController implements Initializable {

    @FXML
    private AnchorPane addcustomerPane;
    @FXML
    private JFXTextField txtfname;
    @FXML
    private JFXTextField txtlname;
    @FXML
    private JFXTextField txtcontact;
    @FXML
    private JFXTextField txtnic;
    @FXML
    private JFXButton btnaddcustomer;
    @FXML
    private TextField txtcustomerid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void addCustomer(ActionEvent event) throws SQLException {
        if(txtnic.getText().isEmpty() || txtfname.getText().isEmpty() || txtlname.getText().isEmpty()
                || txtcontact.getText().isEmpty()){
            
            MessageAlert.ShowMessage("Missing Data", "Alert", Alert.AlertType.ERROR);
            
        }else{
            new CustomerController().saveCustomer(new Customer(txtcustomerid.getText(),txtfname.getText(),
                txtlname.getText(),Integer.parseInt(txtcontact.getText()), txtnic.getText()));
            clearFields();
        }
                
        
    }

    @FXML
    private void loadCustomerID(KeyEvent event) {
        txtcustomerid.setText(new CustomerController().setCustomerID());
    }
    
    private void clearFields(){
        txtcustomerid.setText(null);
        txtfname.setText(null);
        txtlname.setText(null);
        txtcontact.setText(null);
        txtnic.setText(null);
    }

    @FXML
    private void charactersOnly(KeyEvent event) {
        TextKeyHandling.onlyCharacters(event);
    }

    @FXML
    private void numbersOnly(KeyEvent event) {
        TextKeyHandling.onlyNumbers(event);
    }
}
