/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import londoncut.alerts.MessageAlert;
import londoncut.models.controller.ProductController;
import londoncut.tables.controller.tblProductDeleteControlelr;
import londoncut.tables.tblProductDelete;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class DeleteproductController implements Initializable {

    @FXML
    private AnchorPane deletePane;
    @FXML
    private JFXTextField txtsearch;

    @FXML
    private JFXButton btndelete;
    @FXML
    private TableView<tblProductDelete> tbldeleteproduct;
    @FXML
    private TableColumn<tblProductDelete, String> colID;
    @FXML
    private TableColumn<tblProductDelete, String> colName;
    @FXML
    private TableColumn<tblProductDelete, Integer> colPrice;
    @FXML
    private TableColumn<tblProductDelete, Integer> colQty;
    @FXML
    private TableColumn<tblProductDelete, String> colDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTable();
        tbldeleteproduct.setItems(new tblProductDeleteControlelr().loadData());

    }

    private void initiateTable() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    @FXML
    private void autoSearch(KeyEvent event) {
        tbldeleteproduct.setItems(new tblProductDeleteControlelr().autoLoad(txtsearch.getText()));
    }

    @FXML
    private void deleteProduct(ActionEvent event) {
        if (tbldeleteproduct.getSelectionModel().isEmpty()) {
            MessageAlert.ShowMessage("No Product is selected", "Alert", Alert.AlertType.INFORMATION);
        } else {
            boolean bool = MessageAlert.confirm(tbldeleteproduct.getSelectionModel().getSelectedItem().getName());
            if (bool) {
                new ProductController().deleteProduct(tbldeleteproduct.getSelectionModel().getSelectedItem().getId());
                tbldeleteproduct.setItems(new tblProductDeleteControlelr().loadData());
            }

        }
    }

}
