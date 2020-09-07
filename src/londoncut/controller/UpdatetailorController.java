
package londoncut.controller;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import londoncut.models.controller.TailorController;
import londoncut.tables.controller.tblUpdateTailorController;
import londoncut.tables.tblUpdateTailor;
import sun.plugin.javascript.navig.Anchor;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class UpdatetailorController implements Initializable {

    
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtsearch;
    @FXML
    private TableColumn<tblUpdateTailor, String> colID;
    @FXML
    private TableColumn<tblUpdateTailor, String> colFname;
    @FXML
    private TableColumn<tblUpdateTailor, String> colLname;
    @FXML
    private TableColumn<tblUpdateTailor, String> colAddress;
    @FXML
    private TableColumn<tblUpdateTailor, Integer> colMobile;
    @FXML
    private TableColumn<tblUpdateTailor, String> colNIC;
    @FXML
    private TableView<tblUpdateTailor> tblupdatetailor;

    @FXML
    private void loadupdatetailordetail(MouseEvent event) throws IOException {
        new TailorController().getSelectedid(tblupdatetailor.getSelectionModel().getSelectedItem().getId());
        AnchorPane pane=FXMLLoader.load(Main.class.getResource("views/updatetailordetail.fxml"));
        root.getChildren().clear();
        root.getChildren().add(pane);
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTable();
        tblupdatetailor.setItems(new tblUpdateTailorController().loadData());
    }    

    private void initiateTable(){
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colLname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        
    }

    @FXML
    private void autoSearch(KeyEvent event) {
        tblupdatetailor.setItems(new tblUpdateTailorController().autoLoad(txtsearch.getText()));
    }
    
}
