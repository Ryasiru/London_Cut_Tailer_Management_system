/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import londoncut.Main;
import londoncut.alerts.MessageAlert;
import londoncut.db.DBConnection;
import londoncut.models.TailorSewWork;
import londoncut.models.controller.CuttingOrderController;
import londoncut.models.controller.MaterialController;
import londoncut.models.controller.TailorSewWorkController;
import londoncut.tables.controller.tblCuttingOrderController;
import londoncut.tables.tblCuttingOrder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class CuttingorderController implements Initializable {

    @FXML
    private AnchorPane cuttingorderPane;
    @FXML
    private JFXButton btnhome;
    @FXML
    private JFXButton btnprint;
    @FXML
    private JFXTextField txtsearch;
    @FXML
    private TableView<tblCuttingOrder> tblcuttingorder;
    @FXML
    private TableColumn<tblCuttingOrder, String> colsuitNumber;
    @FXML
    private TableColumn<tblCuttingOrder, String> colType;
    @FXML
    private TableColumn<tblCuttingOrder, String> colMeasurement;
    @FXML
    private TableColumn<tblCuttingOrder, String> colMaterial;
    @FXML
    private TableColumn<tblCuttingOrder, String> colQuality;
    @FXML
    private TableColumn<tblCuttingOrder, Integer> colQty;
    @FXML
    private TableColumn<tblCuttingOrder, String> colDeliverydate;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtmaterial;
    @FXML
    private TextField txtqty;
    @FXML
    private TextField txtquality;
    @FXML
    private TableColumn<tblCuttingOrder, String> colOrder;
    @FXML
    private JFXButton btnback;
    

    
    @FXML
    private void backhome(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
        Scene scene=new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition ft=new FadeTransition();
        ft.setDuration(Duration.millis(1000));
        ft.setNode(cuttingorderPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        
        initiateTable();
        tblcuttingorder.setItems(new tblCuttingOrderController().loadData());
        
    }    

    @FXML
    private void printOrder(ActionEvent event) throws FileNotFoundException, JRException {
        if(tblcuttingorder.getSelectionModel().isEmpty()){
            MessageAlert.ShowMessage("No data is Selected...", "Error", Alert.AlertType.ERROR);
        }else if(txtqty.getText().isEmpty()){
            MessageAlert.ShowMessage("No quantity is written...", "Error", Alert.AlertType.ERROR);
        }else{
            new MaterialController().updateQty(txtmaterial.getText(), txtquality.getText(), Double.parseDouble(txtqty.getText()));
            new TailorSewWorkController().saveTailorSewWork(new TailorSewWork(tblcuttingorder.getSelectionModel().getSelectedItem().getOrdernumber(),
                    tblcuttingorder.getSelectionModel().getSelectedItem().getSuitnumber(),
                    tblcuttingorder.getSelectionModel().getSelectedItem().getType(), tblcuttingorder.getSelectionModel().getSelectedItem().getMeasurement(),
                    tblcuttingorder.getSelectionModel().getSelectedItem().getDeliverydate(),tblcuttingorder.getSelectionModel().getSelectedItem().getQty()));
            printCuttingOrder();
            new CuttingOrderController().deleteCuttingOrder(tblcuttingorder.getSelectionModel().getSelectedItem().getSuitnumber());
            tblcuttingorder.setItems(new tblCuttingOrderController().loadData());
            clearFields();
           
        }
        
    }

    
    private void initiateTable(){
        colsuitNumber.setCellValueFactory(new PropertyValueFactory<>("suitnumber"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colMeasurement.setCellValueFactory(new PropertyValueFactory<>("measurement"));
        colMaterial.setCellValueFactory(new PropertyValueFactory<>("material"));
        colQuality.setCellValueFactory(new PropertyValueFactory<>("quality"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDeliverydate.setCellValueFactory(new PropertyValueFactory<>("deliverydate"));
        colOrder.setCellValueFactory(new PropertyValueFactory<>("ordernumber"));
    }

    @FXML
    private void loadFields(MouseEvent event) {
        txtid.setText(tblcuttingorder.getSelectionModel().getSelectedItem().getSuitnumber());
        txtmaterial.setText(tblcuttingorder.getSelectionModel().getSelectedItem().getMaterial());
        txtquality.setText(tblcuttingorder.getSelectionModel().getSelectedItem().getQuality());
        
    }
    
    private void clearFields(){
        txtid.setText(null);
        txtmaterial.setText(null);
        txtqty.setText(null);
        txtquality.setText(null);
        txtsearch.setText(null);
    }

    @FXML
    private void autoLoad(KeyEvent event) {
           tblcuttingorder.setItems(new tblCuttingOrderController().autoLoad(txtsearch.getText()));
    }
    
    private void printCuttingOrder() throws FileNotFoundException, JRException{
        InputStream in = new FileInputStream("E:\\JavJ\\londoncut\\src\\londoncut\\report\\CuttingOrder.jrxml");
        HashMap para = new HashMap();
        para.put("ordernumber", tblcuttingorder.getSelectionModel().getSelectedItem().getOrdernumber());
        para.put("suitnumber",tblcuttingorder.getSelectionModel().getSelectedItem().getSuitnumber());
        JasperReport jr = JasperCompileManager.compileReport(in);
        JasperPrint jp = JasperFillManager.fillReport(jr, para, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jp, false);

    }

   
    
    
}
