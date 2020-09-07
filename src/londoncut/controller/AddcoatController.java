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
import javafx.scene.layout.AnchorPane;
import londoncut.alerts.MessageAlert;
import londoncut.models.Coat;
import londoncut.models.controller.CoatController;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class AddcoatController implements Initializable {

    @FXML
    private AnchorPane addcoatPane;
    @FXML
    private TextField txtcoatnumber;
    @FXML
    private JFXTextField txtcoatcolour;
    @FXML
    private JFXTextField txtprice;
    @FXML
    private JFXButton btnaddcoat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtcoatnumber.setText(new CoatController().setCoatNumber());
    }    

    @FXML
    private void addCoat(ActionEvent event) {
        if(txtcoatcolour.getText().isEmpty()||txtprice.getText().isEmpty()){
            MessageAlert.ShowMessage("Missing Data...", "Alert", Alert.AlertType.ERROR);
        }else{
            new CoatController().saveCoat(new Coat(txtcoatnumber.getText(),txtcoatcolour.getText(),
                    Integer.parseInt(txtprice.getText()),"Available"));
            clearFields();
            txtcoatnumber.setText(new CoatController().setCoatNumber());
            MessageAlert.ShowMessage("New Coat Added..", "Done", Alert.AlertType.INFORMATION);
        }
    }
    
    private void clearFields(){
        txtcoatcolour.setText(null);
        txtprice.setText(null);
    }
}
