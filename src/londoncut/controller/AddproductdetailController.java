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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import londoncut.alerts.MessageAlert;
import londoncut.db.DBConnection;
import londoncut.events.TextKeyHandling;
import londoncut.models.Product;
import londoncut.models.controller.ProductController;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class AddproductdetailController implements Initializable {

    @FXML
    private JFXTextField txtproductname;
    @FXML
    private JFXTextField txtqty;
    @FXML
    private JFXTextField txtdate;
    @FXML
    private JFXButton btnupdate;
    @FXML
    private JFXTextField txtprice;
    @FXML
    private TextField txtid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtid.setText(new ProductController().setSelectedId());
        loadDataToFields();
    }    
    
    private void loadDataToFields(){
        String sql="SELECT * FROM product WHERE productid=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,txtid.getText());
            ResultSet result=pstm.executeQuery();
            if(result.next()){
                txtproductname.setText(result.getString("productname"));
                txtprice.setText(result.getString("unitprice"));
                txtqty.setText(result.getString("qty"));
                txtdate.setText(result.getString("purchaseddate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddproductdetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void updateProduct(ActionEvent event) {
        if(txtproductname.getText().isEmpty()||txtprice.getText().isEmpty()||txtqty.getText().isEmpty()||txtdate.getText().isEmpty()){
            MessageAlert.ShowMessage("Missing Data..", "Error", Alert.AlertType.ERROR);
        }else{
            new ProductController().updateProduct(new Product(txtid.getText(), txtproductname.getText(),
                    Integer.parseInt(txtprice.getText()), Integer.parseInt(txtqty.getText()),txtdate.getText()));
            MessageAlert.ShowMessage("Update Successful...", "Done", Alert.AlertType.INFORMATION);
        }
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
