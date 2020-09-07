
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
import londoncut.models.Material;
import londoncut.models.controller.MaterialController;


public class UpdatematerialdetailController implements Initializable {

    @FXML
    private JFXButton btndone;
    @FXML
    private TextField txtid;
    @FXML
    private JFXTextField txtname;
    @FXML
    private JFXTextField txtquantity;
    @FXML
    private JFXTextField txtqty;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtid.setText(new MaterialController().setSelectedMatID() );
        ArrayList<String >list=new MaterialController().loadSelectedData(txtid.getText());
        txtname.setText(list.get(0));
        txtquantity.setText(list.get(1));
        txtqty.setText(list.get(2));
    }    

    @FXML
    private void updateMaterial(ActionEvent event) {
        if(txtname.getText().isEmpty()||txtqty.getText().isEmpty()||txtquantity.getText().isEmpty()){
            MessageAlert.ShowMessage("Missing Data ....", "Error", Alert.AlertType.INFORMATION);
        }else{
            new MaterialController().updateMaterial(new Material(txtid.getText(), txtname.getText(),
                    txtquantity.getText(),Double.parseDouble(txtqty.getText())));
            MessageAlert.ShowMessage("Updated Successfully... ", "Done", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void characterOnly(KeyEvent event) {
        TextKeyHandling.onlyCharacters(event);
    }
    
    
    
}
