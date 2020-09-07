/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import static java.lang.String.valueOf;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import londoncut.Main;
import londoncut.alerts.MessageAlert;
import londoncut.db.DBConnection;
import londoncut.events.TextKeyHandling;
import londoncut.models.controller.OrdersController;
import londoncut.models.controller.PaymentController;
import londoncut.tables.controller.tblPaymentController;
import londoncut.tables.tblPayment;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class Customerpayment_2Controller implements Initializable {

    @FXML
    private JFXButton btnprint;
    @FXML
    private TableView<tblPayment> tblorders;
    @FXML
    private TableColumn<tblPayment, String> colType;
    @FXML
    private TableColumn<tblPayment, Integer> colQty;
    @FXML
    private TextField txttotal;
    @FXML
    private TextField txtordernumber;
    @FXML
    private TextField txtcustomerid;
    @FXML
    private TextField txtcustomername;
    @FXML
    private TextField txtpayment;
    
    static String payment;

    
    private void loadhome(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
        Scene scene=new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       colType.setCellValueFactory(new PropertyValueFactory<>("type"));
       colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
       txtordernumber.setText(new OrdersController().setSelectOrder());
       loadDataToFields();
       tblorders.setItems(new tblPaymentController().loadTable(txtordernumber.getText()));
    }    

    private void loadDataToFields(){
        String sql="SELECT c.customerid , fname , lname  FROM orderdetail od , customer c WHERE od.ordernumber=? AND od.customerid=c.customerid ";
        String sqlTotal="SELECT payment ,(total-payment) as Total FROM payment WHERE ordernumber=? ";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            PreparedStatement pstmTotal=con.prepareStatement(sqlTotal);
            pstm.setString(1,txtordernumber.getText());
            pstmTotal.setString(1,txtordernumber.getText());
            ResultSet result=pstm.executeQuery();
            ResultSet resultTotal=pstmTotal.executeQuery();
            
            while(result.next()){
                txtcustomerid.setText(result.getString("customerid"));
                txtcustomername.setText(result.getString("fname")+" "+result.getString("lname"));
                
            }
            if(resultTotal.next()){
                txttotal.setText(resultTotal.getString("Total"));
                payment=resultTotal.getString("payment");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customerpayment_2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void updatePayment(ActionEvent event) throws IOException {
        if(txtpayment.getText().isEmpty()){
            MessageAlert.ShowMessage("No Payment Have Done....","Payment Error", Alert.AlertType.ERROR);
        }else{
            new PaymentController().updatePayment(payment, txtpayment.getText(), txtordernumber.getText());
            MessageAlert.ShowMessage("Payment Successful....", "Payment Info", Alert.AlertType.INFORMATION);
            Parent root=FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
            Scene scene=new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        
    }

    @FXML
    private void numberOnly(KeyEvent event) {
        TextKeyHandling.onlyNumbers(event);
    }
    
}
