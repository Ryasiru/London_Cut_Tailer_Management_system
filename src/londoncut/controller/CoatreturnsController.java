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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import londoncut.models.controller.CoatRentController;
import londoncut.models.controller.RentCoatDetailController;
import londoncut.tables.controller.tblReturnCoatController;
import londoncut.tables.tblReturnCoat;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class CoatreturnsController implements Initializable {

    @FXML
    private JFXButton btnhome;
    @FXML
    private AnchorPane coatreturnPane;
    @FXML
    private JFXTextField txtsearch;
    @FXML
    private TableView<tblReturnCoat> tblreturncoat;
    @FXML
    private TableColumn<tblReturnCoat, String> colNIC;
    @FXML
    private TableColumn<tblReturnCoat, String> colCustomerName;
    @FXML
    private TableColumn<tblReturnCoat, String> colNumber;
    @FXML
    private TableColumn<tblReturnCoat, String> colRentDate;
    @FXML
    private TableColumn<tblReturnCoat, String> colReturnDate;
    @FXML
    private TextField txttodaydate;
    @FXML
    private TextField txtreturndate;
    @FXML
    private TextField txtfine;
    @FXML
    private JFXButton btndone;

    @FXML
    private void loadhome(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
        Scene scene=new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        
        
        
                
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition ft=new FadeTransition();
        ft.setDuration(Duration.millis(1000));
        ft.setNode(coatreturnPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        
        initiateTable();
        tblreturncoat.setItems(new tblReturnCoatController().loadData());
    }    

    private void initiateTable(){
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("coatnumber"));
        colRentDate.setCellValueFactory(new PropertyValueFactory<>("rentdate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returndate"));
    }

    @FXML
    private void getDataToFields(MouseEvent event) {
        Date date = new Date();
        Instant instant = date.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        txttodaydate.setText(localDate.toString());
        txtreturndate.setText(tblreturncoat.getSelectionModel().getSelectedItem().getReturndate());
        int datediff=getDateDiff();
        if(datediff<0){
            txtfine.setText("0");
        }else{
            int fine=datediff*500;
            txtfine.setText(String.valueOf(fine));
            
        }
    }

    @FXML
    private void updateCoatStatus(ActionEvent event) {
        if(txtreturndate.getText().isEmpty()||txtfine.getText().isEmpty()){
            MessageAlert.ShowMessage("No Data Selected...","Alert", Alert.AlertType.INFORMATION);
        }else{
            new RentCoatDetailController().setFine(Double.parseDouble(txtfine.getText()),
            tblreturncoat.getSelectionModel().getSelectedItem().getCoatnumber(), txtreturndate.getText());
            new CoatRentController().updateCoatStatus(tblreturncoat.getSelectionModel().getSelectedItem().getCoatnumber());
            new CoatRentController().deleteCoatRent(tblreturncoat.getSelectionModel().getSelectedItem().getNic(), 
            tblreturncoat.getSelectionModel().getSelectedItem().getRentdate(),
            tblreturncoat.getSelectionModel().getSelectedItem().getCoatnumber());
            tblreturncoat.getItems().removeAll(tblreturncoat.getSelectionModel().getSelectedItem());
            clearFields();
        }
        
        
    }
    
    private int getDateDiff(){
        
        String sql="SELECT DATEDIFF(?,?) AS DateDiff;";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, txttodaydate.getText());
            pstm.setString(2, txtreturndate.getText());
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                return result.getInt("DateDiff");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CoatreturnsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }

    @FXML
    private void autoSearch(KeyEvent event) {
        tblreturncoat.setItems(new tblReturnCoatController().autoLoad(txtsearch.getText()));
    }
    
    private void clearFields(){
        txtreturndate.setText(null);
        txtfine.setText(null);
    }
}
