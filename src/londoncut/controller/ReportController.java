/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import javafx.stage.Stage;
import londoncut.Main;
import londoncut.alerts.MessageAlert;
import londoncut.db.DBConnection;
import londoncut.tables.controller.tblExpenseController;
import londoncut.tables.tblExpense;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class ReportController implements Initializable {

    @FXML
    private JFXButton btnsearch;
    @FXML
    private JFXButton btnhome;
    @FXML
    private JFXButton btnprint;
    @FXML
    private TextField txtsuit;
    @FXML
    private TextField txtrent;
    @FXML
    private TextField txtfine;
    @FXML
    private TextField txtproduct;
    @FXML
    private TextField txtincome;
    @FXML
    private TableView<tblExpense> tblexpense;
    @FXML
    private TableColumn<tblExpense, String> colDate;
    @FXML
    private TableColumn<tblExpense, String> colDesc;
    @FXML
    private TableColumn<tblExpense, Double> colExp;
    @FXML
    private TextField txtexpense;
   
    @FXML
    private JFXDatePicker fromDate;
    @FXML
    private JFXDatePicker toDAte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTable();
        setIncome();
        tblexpense.setItems(new tblExpenseController().loadData());
        setTotal();
    }    

    @FXML
    private void loadHome(ActionEvent event) throws IOException {
        Parent next=FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
        Scene scene=new Scene(next);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    private void initiateTable(){
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colExp.setCellValueFactory(new PropertyValueFactory<>("expense"));
    }
    
    private void setTotal(){
        double total=Double.parseDouble(txtsuit.getText())+Double.parseDouble(txtrent.getText())+Double.parseDouble(txtfine.getText())
                +Double.parseDouble(txtproduct.getText());
        txtincome.setText(String.valueOf(total));
        
        double exp=0;
        TableColumn<tblExpense,Double>column=colExp;
        List<Double> list=new ArrayList();
        for (tblExpense item : tblexpense.getItems()) {
            list.add(column.getCellObservableValue(item).getValue());
        }
        for (int i = 0; i < tblexpense.getItems().size(); i++) {
            exp+=list.get(i);
            
        }
        
        txtexpense.setText(String.valueOf(exp));
    }
    
    private void setIncome(){
        String suit="SELECT SUM(payment) AS total FROM payment";
        String rent="SELECT SUM(rentprice) AS total FROM rentcoatdetail";
        String fine="SELECT SUM(fine) AS total FROM rentcoatdetail";
        String product="SELECT SUM(price) AS total FROM sellproduct";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstmsuit=con.prepareStatement(suit);
            PreparedStatement pstmrent=con.prepareStatement(rent);
            PreparedStatement pstmfine=con.prepareStatement(fine);
            PreparedStatement pstmproduct=con.prepareStatement(product);
            
            ResultSet resultsuit=pstmsuit.executeQuery();
            while(resultsuit.next()){
                txtsuit.setText(resultsuit.getString("total"));
            }
            
            ResultSet resultrent=pstmrent.executeQuery();
            while(resultrent.next()){
                txtrent.setText(resultrent.getString("total"));
            }
            
            ResultSet resultfine=pstmfine.executeQuery();
            while(resultfine.next()){
                txtfine.setText(resultfine.getString("total"));
            }
            ResultSet resultproduct=pstmproduct.executeQuery();
            while(resultproduct.next()){
                txtproduct.setText(resultproduct.getString("total"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setIncome(String from,String to){
        String suit="SELECT SUM(payment) AS total FROM payment WHERE paymentdate BETWEEN ? AND ?";
        String rent="SELECT SUM(rentprice) AS total FROM rentcoatdetail WHERE returndate BETWEEN ? AND ?";
        String fine="SELECT SUM(fine) AS total FROM rentcoatdetail WHERE returndate BETWEEN ? AND ?";
        String product="SELECT SUM(price) AS total FROM sellproduct sp ,orders o WHERE sp.ordernumber=o.ordernumber AND orderdate BETWEEN ? AND ?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstmsuit=con.prepareStatement(suit);
            PreparedStatement pstmrent=con.prepareStatement(rent);
            PreparedStatement pstmfine=con.prepareStatement(fine);
            PreparedStatement pstmproduct=con.prepareStatement(product);
            
            pstmsuit.setString(1,from);
            pstmsuit.setString(2,to);
            
            pstmrent.setString(1,from);
            pstmrent.setString(2,to);
            
            pstmfine.setString(1,from);
            pstmfine.setString(2,to);
            
            pstmproduct.setString(1,from);
            pstmproduct.setString(2,to);
            
            ResultSet resultsuit=pstmsuit.executeQuery();
            while(resultsuit.next()){
                txtsuit.setText(resultsuit.getString("total"));
            }
            
            ResultSet resultrent=pstmrent.executeQuery();
            while(resultrent.next()){
                txtrent.setText(resultrent.getString("total"));
            }
            
            ResultSet resultfine=pstmfine.executeQuery();
            while(resultfine.next()){
                txtfine.setText(resultfine.getString("total"));
            }
            ResultSet resultproduct=pstmproduct.executeQuery();
            while(resultproduct.next()){
                txtproduct.setText(resultproduct.getString("total"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchData(ActionEvent event) {
        if(fromDate.getValue().format(DateTimeFormatter.ISO_DATE).isEmpty()||toDAte.getValue().format(DateTimeFormatter.ISO_DATE).isEmpty()){
            MessageAlert.ShowMessage("Missing Date", "Error", Alert.AlertType.ERROR);
        }else{
            setIncome(fromDate.getValue().format(DateTimeFormatter.ISO_DATE), toDAte.getValue().format(DateTimeFormatter.ISO_DATE));
            tblexpense.setItems(new tblExpenseController().searchExp(fromDate.getValue().format(DateTimeFormatter.ISO_DATE),
                    toDAte.getValue().format(DateTimeFormatter.ISO_DATE)));
            setTotal();
        }
        
    }

    @FXML
    private void setFromDate(ActionEvent event) {
        if(fromDate.getValue()!=null){
            String local=fromDate.getValue().format(DateTimeFormatter.ISO_DATE);
            fromDate.getEditor().setText(local);
        }else{
            fromDate.getEditor().setText("YY/MM/DD");
        }

    }

    @FXML
    private void setToDate(ActionEvent event) {
        if(toDAte.getValue()!=null){
            String local=toDAte.getValue().format(DateTimeFormatter.ISO_DATE);
            toDAte.getEditor().setText(local);
        }else{
            toDAte.getEditor().setText("YY/MM/DD");
        }

    }
}
