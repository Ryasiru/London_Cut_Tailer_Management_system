/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
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
import londoncut.events.TextKeyHandling;
import londoncut.models.controller.TailorSewWorkController;
import londoncut.tables.controller.tblTailorController;
import londoncut.tables.controller.tblTailorSewController;
import londoncut.tables.tblTailor;
import londoncut.tables.tblTailorSew;
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
public class TailoringorderController implements Initializable {

    @FXML
    private AnchorPane tailoringorderPane;
    @FXML
    private JFXButton btnhome;
    @FXML
    private JFXButton btnprint;
    @FXML
    private TableView<tblTailor > tbltailor;
    @FXML
    private TextField txtsuittype;
    @FXML
    private TextField txtdeliverydate;
    @FXML
    private TextField txtmeasurement;
    @FXML
    private TextField txttailorprice;
    @FXML
    private TextField txttailorid;
    @FXML
    private TextField txttailorname;
    @FXML
    private TableView<tblTailorSew> tblOrder;
    @FXML
    private TableColumn<tblTailorSew, String> colSuitid;
    @FXML
    private TableColumn<tblTailorSew, String> colType;
    @FXML
    private TableColumn<tblTailorSew, String> colMeasurement;
    @FXML
    private TableColumn<tblTailorSew, String> colDeliverydate;
    @FXML
    private TableColumn<tblTailorSew, String> colQty;
    @FXML
    private TextField txtsuitnumber;
    @FXML
    private TextField txtqty;
    @FXML
    private TableColumn<tblTailor, String> colTailorID;
    @FXML
    private TableColumn<tblTailor, String> colTailorname;
    @FXML
    private TableColumn<tblTailorSew, String> colOrdernumber;
    @FXML
    private TextField txtordernumber;
  

    @FXML
    private void loadhome(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
        Scene scene=new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition ft=new FadeTransition();
        ft.setDuration(Duration.millis(1000));
        ft.setNode(tailoringorderPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        
        initiateTables();
        tblOrder.setItems(new tblTailorSewController().loadOrder());
        tbltailor.setItems(new tblTailorController().loadTailorData());
    }    

    private void initiateTables(){
        colSuitid.setCellValueFactory(new PropertyValueFactory<>("suitnumber"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colMeasurement.setCellValueFactory(new PropertyValueFactory<>("measurement"));
        colDeliverydate.setCellValueFactory(new PropertyValueFactory<>("deliverydate"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTailorID.setCellValueFactory(new PropertyValueFactory<>("tailorid"));
        colTailorname.setCellValueFactory(new PropertyValueFactory<>("tailorname"));
        colOrdernumber.setCellValueFactory(new PropertyValueFactory<>("ordernumber"));
    }

    @FXML
    private void getSuitData(MouseEvent event) {
        txtordernumber.setText(tblOrder.getSelectionModel().getSelectedItem().getOrdernumber());
        txtsuitnumber.setText(tblOrder.getSelectionModel().getSelectedItem().getSuitnumber());
        txtsuittype.setText(tblOrder.getSelectionModel().getSelectedItem().getType());
        txtmeasurement.setText(tblOrder.getSelectionModel().getSelectedItem().getMeasurement());
        txtdeliverydate.setText(tblOrder.getSelectionModel().getSelectedItem().getDeliverydate());
        txtqty.setText(String.valueOf(tblOrder.getSelectionModel().getSelectedItem().getQty()));
        new TailorSewWorkController().setTot(tblOrder.getSelectionModel().getSelectedItem().getQty());
    }

    @FXML
    private void getTailorData(MouseEvent event) {
        txttailorid.setText(tbltailor.getSelectionModel().getSelectedItem().getTailorid());
        txttailorname.setText(tbltailor.getSelectionModel().getSelectedItem().getTailorname());
    }

    @FXML
    private void addTailorWork(ActionEvent event) throws FileNotFoundException, JRException {
        if(txtsuitnumber.getText().isEmpty()){
            MessageAlert.ShowMessage("No Order is Selected", "Order Error", Alert.AlertType.ERROR);
        }else if(txttailorid.getText().isEmpty()){
            MessageAlert.ShowMessage("No Tailor is Selected", "Tailor Error", Alert.AlertType.ERROR);
        }else if(txttailorprice.getText().isEmpty()){
            MessageAlert.ShowMessage("Tailor Price is Empty", "Alert", Alert.AlertType.WARNING);
        }else{
            printTailorOrder();
            double total=Integer.parseInt(txtqty.getText())*Integer.parseInt(txttailorprice.getText());
            new TailorSewWorkController().addTailorWork(txtsuitnumber.getText(), txttailorid.getText(),
                    Integer.parseInt(txtqty.getText()),total);
            new TailorSewWorkController().updateQty(txtsuitnumber.getText(), Integer.parseInt(txtqty.getText()));
            new TailorSewWorkController().updateSalary(txttailorid.getText(), total);
            if(tblOrder.getSelectionModel().getSelectedItem().getQty()==0){
                tblOrder.getItems().removeAll(tblOrder.getSelectionModel().getSelectedItem());
            }
            tblOrder.setItems(new tblTailorSewController().loadOrder());
            clearFields();
        }
    }
    
    private void clearFields(){
        txtsuitnumber.setText(null);
        txtsuittype.setText(null);
        txtmeasurement.setText(null);
        txtdeliverydate.setText(null);txtqty.setText(null);txttailorid.setText(null);
        txttailorname.setText(null);txttailorprice.setText(null);
    }

    @FXML
    private void numbersOnly(KeyEvent event) {
        TextKeyHandling.onlyNumbers(event);
    }
    
    private void printTailorOrder() throws FileNotFoundException, JRException{
        int total=Integer.parseInt(txtqty.getText())*Integer.parseInt(txttailorprice.getText());
        InputStream in = new FileInputStream("E:\\JavJ\\londoncut\\src\\londoncut\\report\\TailorOrder.jrxml");
        HashMap para = new HashMap();
        para.put("tailorid",txttailorid.getText());
        para.put("tailorname",txttailorname.getText());
        para.put("tailorprice",String.valueOf(total));
        
        para.put("ordernumber",txtordernumber.getText());
        para.put("suitid",txtsuitnumber.getText());
        para.put("type",txtsuittype.getText());
        para.put("qty",txtqty.getText());
        para.put("deliverydate",txtdeliverydate.getText());
        para.put("measurement",txtmeasurement.getText());
        
        JasperReport jr = JasperCompileManager.compileReport(in);
        JasperPrint jp = JasperFillManager.fillReport(jr, para, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jp, false);
    }
}

