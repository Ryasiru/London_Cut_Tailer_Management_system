
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
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
public class UpdatetailordetailController implements Initializable {

    @FXML
    private TextField txtid;
    @FXML
    private JFXTextField txtfname;
    @FXML
    private JFXTextField txtlname;
    @FXML
    private JFXTextField txtaddress;
    @FXML
    private JFXTextField txtmobile;
    @FXML
    private JFXTextField txthome;
    @FXML
    private JFXButton btnupdate;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtid.setText(new TailorController().setSelectedid());
        ArrayList<String>list=new TailorController().loadSelectedDetail(txtid.getText());
        txtfname.setText(list.get(0));
        txtlname.setText(list.get(1));
        txtaddress.setText(list.get(2));
        txthome.setText(list.get(3));
        txtmobile.setText(list.get(4));
        
    }    

    @FXML
    private void updateTailor(ActionEvent event) {
        if(txtfname.getText().isEmpty()||txtlname.getText().isEmpty()||txtaddress.getText().isEmpty()||
                txtmobile.getText().isEmpty()){
            MessageAlert.ShowMessage("Missing Data...", "Error", Alert.AlertType.INFORMATION);
        }else{
            new TailorController().updateTailor(new Tailor(txtid.getText(), txtfname.getText(), txtlname.getText(), 
                    txtaddress.getText(), Integer.parseInt(txthome.getText()),Integer.parseInt(txtmobile.getText()), ""));
            
            MessageAlert.ShowMessage("Updated Successfully..", "Done", Alert.AlertType.INFORMATION);
          
        }
    }

    @FXML
    private void characterOnly(KeyEvent event) {
        TextKeyHandling.onlyCharacters(event);
    }

    @FXML
    private void numberOnly(KeyEvent event) {
        TextKeyHandling.onlyCharacters(event);
    }
    
}
