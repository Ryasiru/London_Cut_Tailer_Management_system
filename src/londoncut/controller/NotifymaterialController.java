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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import londoncut.Main;
import londoncut.tables.controller.tblMaterialController;
import londoncut.tables.tblMaterial;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class NotifymaterialController implements Initializable {

    @FXML
    private TableView<tblMaterial> tblmaterial;
    @FXML
    private TableColumn<tblMaterial, String> colNumber;
    @FXML
    private TableColumn<tblMaterial, String> colMaterial;
    @FXML
    private TableColumn<tblMaterial, String> colQuality;
    @FXML
    private TableColumn<tblMaterial, Double> colQty;
    @FXML
    private JFXTextField txtsearch;
    @FXML
    private JFXButton btnhome;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTable();
        tblmaterial.setItems(new tblMaterialController().loadData() );
    }    
     private void initiateTable(){
         colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
         colMaterial.setCellValueFactory(new PropertyValueFactory<>("material"));
         colQuality.setCellValueFactory(new PropertyValueFactory<>("quality"));
         colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
     }

    @FXML
    private void loadHome(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
        Scene scene=new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void autoSearch(KeyEvent event) {
        tblmaterial.setItems(new tblMaterialController().autoLoad(txtsearch.getText()));
    }
}
