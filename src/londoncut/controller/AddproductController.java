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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import londoncut.alerts.MessageAlert;
import londoncut.events.TextKeyHandling;
import londoncut.models.Product;
import londoncut.models.controller.ProductController;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class AddproductController implements Initializable {

    @FXML
    private AnchorPane addproduct;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtproductname;
    @FXML
    private JFXTextField txtqty;
    @FXML
    private JFXTextField txtpurchaseddate;
    @FXML
    private JFXButton btnadd;
    @FXML
    private TextField txtproductid;
    @FXML
    private JFXTextField txtunitprice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtproductid.setText(new ProductController().setProductID());
    }    

    @FXML
    private void addProduct(ActionEvent event) {
        if(txtproductname.getText().isEmpty()||txtunitprice.getText().isEmpty()||txtqty.getText().isEmpty()||txtpurchaseddate.getText().isEmpty()){
            MessageAlert.ShowMessage("Missing Data...", "Error", Alert.AlertType.INFORMATION);
        }else{
            new ProductController().saveProduct(new Product(txtproductid.getText(), txtproductname.getText(), 
                    Integer.parseInt(txtunitprice.getText()),Integer.parseInt(txtqty.getText()), txtpurchaseddate.getText()));
            MessageAlert.ShowMessage("Product addded...", "Done", Alert.AlertType.INFORMATION);
            clearFields();
            txtproductid.setText(new ProductController().setProductID());
        }
    }
    
    private void clearFields(){
        txtproductid.setText(null);
        txtproductname.setText(null);
        txtpurchaseddate.setText(null);
        txtqty.setText(null);
        txtunitprice.setText(null);
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
