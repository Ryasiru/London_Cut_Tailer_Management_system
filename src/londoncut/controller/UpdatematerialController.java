
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import londoncut.Main;
import londoncut.models.controller.MaterialController;
import londoncut.tables.controller.tblUpdateMaterialController;
import londoncut.tables.tblUpdateMaterial;


public class UpdatematerialController implements Initializable {

    @FXML
    private TableView<tblUpdateMaterial> tblupdatematerial;
    @FXML
    private TableColumn<tblUpdateMaterial, String> colID;
    @FXML
    private TableColumn<tblUpdateMaterial, String> colName;
    @FXML
    private TableColumn<tblUpdateMaterial, String> colQuality;
    @FXML
    private TableColumn<tblUpdateMaterial, Double> colQty;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtsearch;
    @FXML
    private AnchorPane updatematerialPane;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTable();
        tblupdatematerial.setItems(new tblUpdateMaterialController().loadData());
    }    

    @FXML
    private void loadUpdateMaterial(MouseEvent event) throws IOException {
        new MaterialController().getSelectedID(tblupdatematerial.getSelectionModel().getSelectedItem().getId());
        AnchorPane pane=FXMLLoader.load(Main.class.getResource("views/updatematerialdetail.fxml") );
        root.getChildren().clear();
        root.getChildren().add(pane);
    }
    
    private void initiateTable(){
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQuality.setCellValueFactory(new PropertyValueFactory<>("quality"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    @FXML
    private void autoSearch(KeyEvent event) {
        tblupdatematerial.setItems(new tblUpdateMaterialController().autoLoad(txtsearch.getText()));
    }


    
}
