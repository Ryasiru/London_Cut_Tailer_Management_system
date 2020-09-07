/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import londoncut.Main;
import londoncut.models.controller.CuttingOrderController;
import londoncut.models.controller.SuitController;
import londoncut.models.controller.SuitOrderController;
import londoncut.tables.controller.tblSuitDetailController;
import londoncut.tables.tblSuitDetail;

public class SuitdetailController implements Initializable {

    @FXML
    private AnchorPane suitdetail;
    @FXML
    private TableColumn<tblSuitDetail, String> colID;
    @FXML
    private TableColumn<tblSuitDetail, String> colType;
    @FXML
    private TableColumn<tblSuitDetail, String> colDate;
    @FXML
    private TableColumn<tblSuitDetail, Integer> colQty;
    @FXML
    private TableColumn<tblSuitDetail, Double> colPrice;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtprice;
    @FXML
    private JFXButton btnadd;
    @FXML
    private JFXButton btndelete;
    @FXML
    private TableView<tblSuitDetail> tblsuitdetail;
    @FXML
    private TextField txtqty;

    private Measurement_4Controller controller;
    
    private double price=0;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTable();
        tblsuitdetail.setItems(new tblSuitDetailController().loadData());
    }

    private void initiateTable() {
        colDate.setCellValueFactory(new PropertyValueFactory<>("deliverydate"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    private void setDataToFields(MouseEvent event) {
        txtid.setText(tblsuitdetail.getSelectionModel().getSelectedItem().getId());
        txtprice.setText(String.valueOf(tblsuitdetail.getSelectionModel().getSelectedItem().getPrice()));
        txtqty.setText(String.valueOf(tblsuitdetail.getSelectionModel().getSelectedItem().getQty()));
    }

    @FXML
    private void updateSuitPrice(ActionEvent event) throws IOException {
        new SuitController().updateSuitPrice(txtid.getText(), Integer.parseInt(txtqty.getText()), Double.parseDouble(txtprice.getText()));
        tblsuitdetail.setItems(new tblSuitDetailController().loadData());
        TableColumn<tblSuitDetail, Double> column = colPrice;
        List<Double> list = new ArrayList();
        for (tblSuitDetail item : tblsuitdetail.getItems()) {
            list.add(column.getCellObservableValue(item).getValue());
        }
        for (int i = 0; i < tblsuitdetail.getItems().size(); i++) {
            price += list.get(i);

        }
        controller.setSuitTotal(price);
        price=0;
        txtid.setText(null);
        txtprice.setText(null);
        txtqty.setText(null);
    }

    public void setController(Measurement_4Controller controller) {
        this.controller = controller;
    }

    @FXML
    private void deleteSuit(ActionEvent event) {
        new CuttingOrderController().deleteCuttingOrder(tblsuitdetail.getSelectionModel().getSelectedItem().getId());
        new SuitOrderController().deleteSuitOrder(tblsuitdetail.getSelectionModel().getSelectedItem().getId());
        new SuitController().deleteSuit(tblsuitdetail.getSelectionModel().getSelectedItem().getId());
        tblsuitdetail.setItems(new tblSuitDetailController().loadData());
    }
}
