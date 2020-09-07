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
import londoncut.models.Material;
import londoncut.models.controller.MaterialController;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class AddmaterialController implements Initializable {

    @FXML
    private JFXTextField txtmaterial;
    @FXML
    private JFXTextField txtquality;
    @FXML
    private JFXButton btnadd;
    @FXML
    private TextField txtmaterialnumber;
    @FXML
    private JFXTextField txtqty;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtmaterialnumber.setText(new MaterialController().setMaterialNumber());
    }    

    @FXML
    private void addNewMaterial(ActionEvent event) {
        if(txtmaterial.getText().isEmpty()||txtquality.getText().isEmpty()||txtqty.getText().isEmpty()){
            MessageAlert.ShowMessage("Missing Data...", "Alert", Alert.AlertType.WARNING);
        }else{
            new MaterialController().saveMaterial(new Material(txtmaterialnumber.getText(), txtmaterial.getText(),
                    txtquality.getText(),Integer.parseInt(txtqty.getText())));
            MessageAlert.ShowMessage("Successful...", "Done", Alert.AlertType.INFORMATION);
            clearFields();
            txtmaterialnumber.setText(new MaterialController().setMaterialNumber());
        }
    }
    
    private void clearFields(){
        txtmaterial.setText(null);
        txtmaterialnumber.setText(null);
        txtqty.setText(null);
        txtquality.setText(null);
        
    }

    @FXML
    private void characterOnly(KeyEvent event) {
        TextKeyHandling.onlyCharacters(event);
    }
  
}
