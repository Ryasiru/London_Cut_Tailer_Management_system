/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package londoncut.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import londoncut.Main;
import londoncut.tables.controller.tblUpdateCustomerController;
import londoncut.tables.tblUpdateCustomer;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class UpdatecustomerController implements Initializable {

    @FXML
    private AnchorPane updatecustomerPane;
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<tblUpdateCustomer> tblupdatecustomer;
    @FXML
    private TableColumn<tblUpdateCustomer,String> colCustomer;
    @FXML
    private TableColumn<tblUpdateCustomer,String> colFname;
    @FXML
    private TableColumn<tblUpdateCustomer,String> colLname;
    @FXML
    private TableColumn<tblUpdateCustomer,Integer> colContact;
    @FXML
    private TableColumn<tblUpdateCustomer,String> colNIC;
    @FXML
    private JFXTextField txtsearchupdate;
    
    public static ObservableList<String> list=FXCollections.observableArrayList();
    
    
    @FXML
    private void loadaddcustomer(MouseEvent event) throws IOException {
        getSelectedData();
        AnchorPane pane=FXMLLoader.load(Main.class.getResource("views/addcustomerdetail.fxml"));
        root.getChildren().clear();
        root.getChildren().add(pane);
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTable();
        tblupdatecustomer.setItems(new tblUpdateCustomerController().loadTable());
    }    
    
    private void initiateTable(){
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customerid"));
        colFname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colLname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
    }
    
    private void getSelectedData(){
        list.add(tblupdatecustomer.getSelectionModel().getSelectedItem().getCustomerid());
        list.add(tblupdatecustomer.getSelectionModel().getSelectedItem().getFname());
        list.add(tblupdatecustomer.getSelectionModel().getSelectedItem().getLname());
        list.add(String.valueOf(tblupdatecustomer.getSelectionModel().getSelectedItem().getContact()));
        list.add(tblupdatecustomer.getSelectionModel().getSelectedItem().getNic());
        
    }
    
    public ObservableList<String> setDatatoFields(){
        return list;
    }

    @FXML
    private void autoSearchCustomer(KeyEvent event) {
        tblupdatecustomer.setItems(new tblUpdateCustomerController().autoLoad(txtsearchupdate.getText()));
    }
    
}
