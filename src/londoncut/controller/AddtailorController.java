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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import londoncut.alerts.MessageAlert;
import londoncut.events.TextKeyHandling;
import londoncut.models.Tailor;
import londoncut.models.controller.TailorController;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class AddtailorController implements Initializable {

    @FXML
    private JFXTextField txtfname;
    @FXML
    private JFXTextField txtlname;
    @FXML
    private JFXTextField txtaddress;
    @FXML
    private JFXTextField txthome;
    @FXML
    private JFXTextField txtnic;
    @FXML
    private JFXTextField txtmobile;
    @FXML
    private JFXButton btnadd;
    @FXML
    private TextField txtid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtid.setText(new TailorController().setTailorID());
        
    }    

    @FXML
    private void addTailor(ActionEvent event) {
        if(txtfname.getText().isEmpty()||txtlname.getText().isEmpty()||txtaddress.getText().isEmpty()||txthome.getText().isEmpty()||
                txtmobile.getText().isEmpty()||txtnic.getText().isEmpty()){
            MessageAlert.ShowMessage("Missing Data...", "Error", Alert.AlertType.ERROR);
        }else{
            new TailorController().saveTailor(new Tailor(txtid.getText(), txtfname.getText(), txtlname.getText(),
                    txtaddress.getText(), Integer.parseInt(txthome.getText()),Integer.parseInt(txtmobile.getText()),txtnic.getText()));
            MessageAlert.ShowMessage("New Tailor Added", "Done", Alert.AlertType.INFORMATION);
            clearFields();
            txtid.setText(new TailorController().setTailorID());
        }
    }
    
    private void clearFields(){
        txtaddress.setText(null);
        txtfname.setText(null);
        txthome.setText(null);
        txtid.setText(null);
        txtlname.setText(null);
        txtmobile.setText(null);
        txtnic.setText(null);
    }

    @FXML
    private void characterOnly(KeyEvent event) {
        TextKeyHandling.onlyCharacters(event);
    }

    @FXML
    private void numberOnly(KeyEvent event) {
        TextKeyHandling.onlyNumbers(event);
    }
    
}
