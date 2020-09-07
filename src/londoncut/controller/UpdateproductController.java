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
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import londoncut.Main;
import londoncut.models.controller.ProductController;
import londoncut.tables.controller.tblUpdateProductController;
import londoncut.tables.tblUpdateProduct;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class UpdateproductController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtsearch;
    @FXML
    private TableView<tblUpdateProduct> tblupdateproduct;
    @FXML
    private TableColumn<tblUpdateProduct, String> colID;
    @FXML
    private TableColumn<tblUpdateProduct, String> colName;
    @FXML
    private TableColumn<tblUpdateProduct, Integer> colPrice;
    @FXML
    private TableColumn<tblUpdateProduct, Integer> colQty;
    @FXML
    private TableColumn<tblUpdateProduct, String> colDate;
        
    
    @FXML
    private void loadaddproduct(MouseEvent event) throws IOException {
        new ProductController().getSelectedId(tblupdateproduct.getSelectionModel().getSelectedItem().getId());
        AnchorPane pane=FXMLLoader.load(Main.class.getResource("views/addproductdetail.fxml"));
        root.getChildren().clear();
        root.getChildren().add(pane);
        
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTable();
        tblupdateproduct.setItems(new tblUpdateProductController().loadData() );
        
    }    

    private void initiateTable(){
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    @FXML
    private void autoSearch(KeyEvent event) {
        tblupdateproduct.setItems(new tblUpdateProductController().autoLoad(txtsearch.getText()));
    }
    

    
    
    
    
}
