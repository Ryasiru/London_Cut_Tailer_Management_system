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
import londoncut.models.controller.MaterialController;
import londoncut.tables.controller.tblDeleteMaterialController;
import londoncut.tables.tblDeleteMaterial;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class DeletematerialController implements Initializable {

    @FXML
    private TableView<tblDeleteMaterial> tbldeletematerial;
    @FXML
    private TableColumn<tblDeleteMaterial, String > colID;
    @FXML
    private TableColumn<tblDeleteMaterial, String> colMaterial;
    @FXML
    private TableColumn<tblDeleteMaterial, String> colQuality;
    @FXML
    private TableColumn<tblDeleteMaterial, Double> colQty;
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
        tbldeletematerial.setItems(new tblDeleteMaterialController().loadData() );
    }    

    @FXML
    private void deleteMaterial(ActionEvent event) {
        if(tbldeletematerial.getSelectionModel().isEmpty()){
            MessageAlert.ShowMessage("No Material is Selected..", "Error", Alert.AlertType.INFORMATION);
        }else{
             boolean bool = MessageAlert.confirm(tbldeletematerial.getSelectionModel().getSelectedItem().getMaterial());
             if(bool){
                 new MaterialController().deleteMaterial(tbldeletematerial.getSelectionModel().getSelectedItem().getMaterialnumber());
                 tbldeletematerial.setItems(new tblDeleteMaterialController().loadData());
             }
            
        }
    }
    
    private void initiateTable(){
        colID.setCellValueFactory(new PropertyValueFactory<>("materialnumber"));
        colMaterial.setCellValueFactory(new PropertyValueFactory<>("material"));
        colQuality.setCellValueFactory(new PropertyValueFactory<>("quality"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    @FXML
    private void autoSearch(KeyEvent event) {
        
        tbldeletematerial.setItems(new tblDeleteMaterialController().autoLoad(txtsearch.getText()));
    }
}
