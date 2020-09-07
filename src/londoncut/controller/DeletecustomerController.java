/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import londoncut.Main;
import londoncut.alerts.MessageAlert;
import londoncut.models.controller.CustomerController;
import londoncut.tables.controller.tblDeleteCustomerController;
import londoncut.tables.tblDeleteCustomer;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class DeletecustomerController implements Initializable {

    @FXML
    private AnchorPane deletecustomerPane;
    @FXML
    private TableView<tblDeleteCustomer> tbldeletecustomer;
    @FXML
    private TableColumn<tblDeleteCustomer, String> colCustomer;
    @FXML
    private TableColumn<tblDeleteCustomer, String> colFname;
    @FXML
    private TableColumn<tblDeleteCustomer, String> colLname;
    @FXML
    private TableColumn<tblDeleteCustomer, Integer> colContact;
    @FXML
    private TableColumn<tblDeleteCustomer, String> colNIC;
    private JFXComboBox<String> comboselectcolumn;
    @FXML
    private JFXTextField txtsearchcustomerdelete;
    @FXML
    private JFXButton btndelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTable();
        tbldeletecustomer.setItems(new tblDeleteCustomerController().loadData());

    }

    @FXML
    private void deleteCustomer(ActionEvent event) {
        boolean bool = MessageAlert.confirm(tbldeletecustomer.getSelectionModel().getSelectedItem().getFname() + " " + tbldeletecustomer.getSelectionModel().getSelectedItem().getLname());
        if (bool) {
            new CustomerController().deleteCustomer(tbldeletecustomer.getSelectionModel().getSelectedItem().getCustomerid());
            tbldeletecustomer.getItems().removeAll(tbldeletecustomer.getSelectionModel().getSelectedItem());
        }

    }

    private void initiateTable() {
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customerid"));
        colFname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colLname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
    }

    @FXML
    private void autoLoadCustomer(KeyEvent event) {
        tbldeletecustomer.setItems(new tblDeleteCustomerController().autoLoad(
                txtsearchcustomerdelete.getText()));
    }
}
