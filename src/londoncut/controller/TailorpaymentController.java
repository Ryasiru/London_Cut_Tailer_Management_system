
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import londoncut.Main;
import londoncut.alerts.MessageAlert;
import londoncut.db.DBConnection;
import londoncut.events.TextKeyHandling;
import londoncut.models.Expense;
import londoncut.models.controller.ExpenseController;
import londoncut.tables.controller.tblTailorPaymentController;
import londoncut.tables.tblTailorPayment;


public class TailorpaymentController implements Initializable {

    @FXML
    private AnchorPane tailorpaymentPane;
    @FXML
    private JFXButton btnhome;
    @FXML
    private TextField txttailorid;
    @FXML
    private TextField txtfname;
    @FXML
    private TextField txtlname;
    @FXML
    private TextField txttotalpayment;
    @FXML
    private JFXTextField txtpayment;
    @FXML
    private TableView<tblTailorPayment> tblpayment;
    @FXML
    private TableColumn<tblTailorPayment,String> colID;
    @FXML
    private TableColumn<tblTailorPayment,String> colFname;
    @FXML
    private TableColumn<tblTailorPayment,String> colLname;
    @FXML
    private TableColumn<tblTailorPayment,Integer> colWork;
    @FXML
    private TableColumn<tblTailorPayment,Double> colPayment;
    @FXML
    private JFXButton btnpay;
    
    static LocalDate localDate;

    @FXML
    private void loadhome(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
        Scene scene=new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition ft=new FadeTransition();
        ft.setDuration(Duration.millis(1000));
        ft.setNode(tailorpaymentPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        
        initiateTable();
        tblpayment.setItems(new tblTailorPaymentController().loadData() );
         Date date = new Date();
        Instant instant = date.toInstant();
        localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }    

    
    private void initiateTable(){
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colLname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colWork.setCellValueFactory(new PropertyValueFactory<>("work"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
    }

    @FXML
    private void loadDatatToFields(MouseEvent event) {
        txttailorid.setText(tblpayment.getSelectionModel().getSelectedItem().getId());
        txtfname.setText(tblpayment.getSelectionModel().getSelectedItem().getFname());
        txtlname.setText(tblpayment.getSelectionModel().getSelectedItem().getLname());
        txttotalpayment.setText(String.valueOf(tblpayment.getSelectionModel().getSelectedItem().getPayment()));
        
    }

    @FXML
    private void updatePayment(ActionEvent event) {
        if(txttailorid.getText().isEmpty()){
            MessageAlert.ShowMessage("No Tailor is Selected", "Error", Alert.AlertType.ERROR);
        }else if(txtpayment.getText().isEmpty()){
            MessageAlert.ShowMessage("No Payment is Done", "Error", Alert.AlertType.ERROR);
        }else{
            double total=Double.parseDouble(txttotalpayment.getText())-Double.parseDouble(txtpayment.getText());
            String sql="UPDATE tailor SET salary=? WHERE tailorid=?";
            Connection con=DBConnection.getInstance().getConnection();
            try {
                PreparedStatement pstm=con.prepareStatement(sql);
                pstm.setDouble(1, total);
                pstm.setString(2, txttailorid.getText());
                pstm.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(TailorpaymentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            new ExpenseController().addExpense(new Expense(localDate.toString(),"Tailor Payment", Double.parseDouble(txtpayment.getText())));
            clearFields();
            tblpayment.setItems(new tblTailorPaymentController().loadData());     
        }
       
    }
    
    private void clearFields(){
        txtfname.setText(null);
        txtlname.setText(null);
        txtpayment.setText(null);
        txttailorid.setText(null);
        txttotalpayment.setText(null);
        
    }

    @FXML
    private void numberOnly(KeyEvent event) {
        TextKeyHandling.onlyNumbers(event);
    }
}
