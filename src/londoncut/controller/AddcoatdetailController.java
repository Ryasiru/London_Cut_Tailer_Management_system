/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import londoncut.alerts.MessageAlert;
import londoncut.db.DBConnection;
import londoncut.events.TextKeyHandling;
import londoncut.models.controller.CoatController;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class AddcoatdetailController implements Initializable {

    @FXML
    private JFXTextField txtprice;
    @FXML
    private JFXButton btnupdate;
    
    @FXML
    private TextField txtcoatnumber;
    @FXML
    private TextField txtcolour;
    @FXML
    private AnchorPane updatedetailpane;
    @FXML
    private ComboBox<String> comboStatus;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtcoatnumber.setText(new CoatController().setSelectedCoatNumber());
        comboStatus.setItems(loadCombo());
        setFields();
        
        
    }    
    
    private void setFields(){
        String sql="SELECT * FROM coat WHERE coatnumber=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, txtcoatnumber.getText());
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                txtcolour.setText(result.getString("coatcolour"));
                txtprice.setText(result.getString("rentprice"));
                comboStatus.getEditor().setText(result.getString("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddcoatdetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateCoat(ActionEvent event) {
        if(txtprice.getText().isEmpty()||comboStatus.getEditor().getText().isEmpty()){
            MessageAlert.ShowMessage("Missing Data...", "Error", Alert.AlertType.ERROR);
        }else{
            new CoatController().updateCoat(txtprice.getText(),comboStatus.getEditor().getText(),txtcoatnumber.getText());
            MessageAlert.ShowMessage("Successful....", "Done", Alert.AlertType.INFORMATION);
            
        }
    }

    @FXML
    private void numberOnly(KeyEvent event) {
        TextKeyHandling.onlyNumbers(event);
    }
    
    private ObservableList<String> loadCombo(){
        ObservableList<String>list=FXCollections.observableArrayList();
        list.add("Available");
        list.add("Rent");
        list.add("Cleaning");
        
        return list;
    }

    @FXML
    private void noEditAllow(KeyEvent event) {
        TextKeyHandling.onlyNumbers(event);
    }
}
