/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import londoncut.models.controller.CoatController;
import londoncut.tables.controller.tblUpdateCoatController;
import londoncut.tables.tblUpdateCoat;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class UpdatecoatController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<tblUpdateCoat> tblupdatecoat;
    @FXML
    private TableColumn<tblUpdateCoat,String> colNumber;
    @FXML
    private TableColumn<tblUpdateCoat,String> colColour;
    @FXML
    private TableColumn<tblUpdateCoat,Integer> colPrice;
    @FXML
    private TableColumn<tblUpdateCoat,String> colStatus;
    @FXML
    private JFXTextField txtsearch;
    
    
    @FXML
    private void loadupdatecoat(MouseEvent event) throws IOException {
        new CoatController().getSelectedCoatNumber(tblupdatecoat.getSelectionModel().getSelectedItem().getCoatnumber());
        AnchorPane pane=FXMLLoader.load(Main.class.getResource("views/updatecoatdetail.fxml"));
        root.getChildren().clear();
        root.getChildren().add(pane);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTable();
        tblupdatecoat.setItems(new tblUpdateCoatController().loadData());
    }    

    
    private void initiateTable(){
        colNumber.setCellValueFactory(new PropertyValueFactory<>("coatnumber"));
        colColour.setCellValueFactory(new PropertyValueFactory<>("colour"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @FXML
    private void autoSearch(KeyEvent event) {
        tblupdatecoat.setItems(new tblUpdateCoatController().autoLoad(txtsearch.getText()));
    }
}
