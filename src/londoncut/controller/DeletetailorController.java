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
import londoncut.models.controller.TailorController;
import londoncut.tables.controller.tblDeleteTailorController;
import londoncut.tables.tblDeleteTailor;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class DeletetailorController implements Initializable {

    @FXML
    private TableView<tblDeleteTailor> tbldeletetailor;
    @FXML
    private TableColumn<tblDeleteTailor, String> colID;
    @FXML
    private TableColumn<tblDeleteTailor, String> colFname;
    @FXML
    private TableColumn<tblDeleteTailor, String> colLname;
    @FXML
    private TableColumn<tblDeleteTailor, String> colAddress;
    @FXML
    private TableColumn<tblDeleteTailor, Integer> colMobile;
    @FXML
    private TableColumn<tblDeleteTailor, String> colNIC;
    @FXML
    private JFXButton btndelete;
    @FXML
    private JFXTextField txtsearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTable();
        tbldeletetailor.setItems(new tblDeleteTailorController().loadData());
    }

    private void initiateTable() {
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colLname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    }

    @FXML
    private void deleteTailor(ActionEvent event) {
        if (tbldeletetailor.getSelectionModel().isEmpty()) {
            MessageAlert.ShowMessage("No Tailor Selected..", "Alert", Alert.AlertType.INFORMATION);
        } else {
            boolean bool = MessageAlert.confirm(tbldeletetailor.getSelectionModel().getSelectedItem().getFname() + " " + tbldeletetailor.getSelectionModel().getSelectedItem().getLname());
            if (bool) {
                new TailorController().deleteTailor(tbldeletetailor.getSelectionModel().getSelectedItem().getId());
                MessageAlert.ShowMessage("Tailor Removed.", "Done", Alert.AlertType.INFORMATION);
                tbldeletetailor.setItems(new tblDeleteTailorController().loadData());
            }

        }
    }

    @FXML
    private void autoSearch(KeyEvent event) {
        tbldeletetailor.setItems(new tblDeleteTailorController().autoLoad(txtsearch.getText()));
    }

}
