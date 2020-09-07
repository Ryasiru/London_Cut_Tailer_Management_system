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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import londoncut.alerts.MessageAlert;
import londoncut.models.controller.CoatController;
import londoncut.tables.controller.tblDeleteCoatController;
import londoncut.tables.tblDeleteCoat;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class DeletecoatController implements Initializable {

    @FXML
    private TableView<tblDeleteCoat> tbldeletecoat;
    @FXML
    private TableColumn<tblDeleteCoat, String> colCoatnumber;
    @FXML
    private TableColumn<tblDeleteCoat, String> colColour;
    @FXML
    private TableColumn<tblDeleteCoat, Integer> colPrice;
    @FXML
    private TableColumn<tblDeleteCoat, String> colStatus;
    @FXML
    private JFXTextField txtsearch;
    @FXML
    private JFXButton btndeletecoat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTable();
        tbldeletecoat.setItems(new tblDeleteCoatController().loadData());
    }

    private void initiateTable() {
        colCoatnumber.setCellValueFactory(new PropertyValueFactory<>("coatnumber"));
        colColour.setCellValueFactory(new PropertyValueFactory<>("colour"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @FXML
    private void deleteCoat(ActionEvent event) {
        if (tbldeletecoat.getSelectionModel().isEmpty()) {
            MessageAlert.ShowMessage("No Coat Selected...", "Error", Alert.AlertType.ERROR);
        } else {
            boolean bool = MessageAlert.confirm(tbldeletecoat.getSelectionModel().getSelectedItem().getCoatnumber());
            if (bool) {
                new CoatController().deleteCoat(tbldeletecoat.getSelectionModel().getSelectedItem().getCoatnumber());
                tbldeletecoat.setItems(new tblDeleteCoatController().loadData());
                MessageAlert.ShowMessage("Successful...", "Done", Alert.AlertType.INFORMATION);
            }

        }
    }

    @FXML
    private void autoSearch(KeyEvent event) {

        tbldeletecoat.setItems(new tblDeleteCoatController().autoLoadData(txtsearch.getText()));
    }
}
