/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.table.DefaultTableModel;
import londoncut.Main;
import londoncut.alerts.MessageAlert;
import londoncut.db.DBConnection;
import londoncut.events.TextKeyHandling;
import londoncut.models.CuttingOrder;
import londoncut.models.OrderDetail;
import londoncut.models.Orders;
import londoncut.models.Suit;
import londoncut.models.SuitOrder;
import londoncut.models.controller.CustomerController;
import londoncut.models.controller.CuttingOrderController;
import londoncut.models.controller.MaterialController;
import londoncut.models.controller.OrderDetailController;
import londoncut.models.controller.OrdersController;
import londoncut.models.controller.SuitController;
import londoncut.models.controller.SuitOrderController;
import londoncut.models.controller.SuitTypeController;
import londoncut.tables.controller.tblMeasurementController;
import londoncut.tables.tblMeasurementData;


/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class Measurement_3Controller implements Initializable {

    @FXML
    private AnchorPane getmeasurementPane;
    @FXML
    private JFXButton btnnext;
    @FXML
    private JFXComboBox<String> combosuittype;
    @FXML
    private JFXComboBox<String> combomaterialtype;
    @FXML
    private JFXTextField txtmeasurement;
    @FXML
    private JFXButton btnadd;
    @FXML
    private JFXComboBox<String> comboquality;
    @FXML
    private TextField txtcustomerid;
    @FXML
    private TextField txtordernumber;
    @FXML
    private TextField txtorderdate;
    @FXML
    private TextField txtsuitid;
    private JFXTextField txtdeliverydate;
    @FXML
    private JFXTextField txtqty;
    @FXML
    private TableColumn<tblMeasurementData,String> colSuitID;
    @FXML
    private TableColumn<tblMeasurementData,String> colMaterial;
    @FXML
    private TableColumn<tblMeasurementData,String> colMeasurement;
    @FXML
    private TableColumn<tblMeasurementData,String> colDeliverydate;
    @FXML
    private TableColumn<tblMeasurementData,String> colType;
    @FXML
    private TableView<tblMeasurementData> tblsuit;
    
    private ObservableList<tblMeasurementData>list=FXCollections.observableArrayList();
    @FXML
    private JFXButton btnclear;
    @FXML
    private TableColumn<tblMeasurementData,Integer> colQty;
    @FXML
    private JFXButton btnupdate;
    @FXML
    private JFXButton btnback;
    @FXML
    private JFXDatePicker datepick;
   
    
    
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
      initiateTable();
      Date date = new Date();
      Instant instant = date.toInstant();
      LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
      txtorderdate.setText(localDate.toString());
      txtcustomerid.setText(new CustomerController().setSelectedCustomerid());
      txtordernumber.setText(new OrdersController().setOrdernumber());
      
      combosuittype.setItems(new SuitTypeController().loadSuitType());
      combomaterialtype.setItems(new MaterialController().loadMaterial());
      comboquality.setItems(new MaterialController().getQuality());
        try {
            new OrdersController().saveOrder(new Orders(txtordernumber.getText(),txtorderdate.getText()));
        } catch (SQLException ex) {
            Logger.getLogger(Measurement_3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
      loadSuitID();
      new OrdersController().getSelectedOrder(txtordernumber.getText());
      
    }
    
    @FXML
    private void getmeasurementpayment(ActionEvent event) throws IOException, SQLException {
       if(tblsuit.getItems().isEmpty()){
            MessageAlert.ShowMessage("No Measurement has Added", "Alert", Alert.AlertType.ERROR);
       }else{
           new OrderDetailController().saveOrderDetail(new OrderDetail(txtordernumber.getText(), txtcustomerid.getText()));
       
       Parent root=FXMLLoader.load(Main.class.getResource("views/measurement_4.fxml"));
       Scene scene=btnnext.getScene();
       root.translateXProperty().set(scene.getWidth());
       getmeasurementPane.getChildren().add(root);
       
       Timeline timeline=new Timeline();
       KeyValue kv=new KeyValue(root.translateXProperty(),0,Interpolator.EASE_IN);
       KeyFrame kf=new KeyFrame(Duration.seconds(1),kv);
       timeline.getKeyFrames().add(kf);
       timeline.setOnFinished(Doevent->{
           getmeasurementPane.getChildren().remove(getmeasurementPane);
       });
       timeline.play();
       }
       
    }

    @FXML
    private void loadQuality(ActionEvent event) {
        comboquality.setItems(new MaterialController().loadQuality(combomaterialtype.getEditor().getText()));
    }

  

    @FXML
    private void addSuitData(ActionEvent event) throws SQLException {
        if(txtmeasurement.getText().isEmpty()||datepick.getEditor().getText().isEmpty()){
            MessageAlert.ShowMessage("Missing Data", "Alert !", Alert.AlertType.WARNING);
        }else{
            
            new SuitController().saveSuit(new Suit(txtsuitid.getText(),combosuittype.getEditor().getText(), 
                    combomaterialtype.getEditor().getText(),comboquality.getEditor().getText(),
                    txtmeasurement.getText(),Integer.parseInt(txtqty.getText()), datepick.getValue().format(DateTimeFormatter.ISO_DATE), 0));
            
            new CuttingOrderController().saveCuttingOrder(new CuttingOrder(txtordernumber.getText(),txtsuitid.getText(),
                    combosuittype.getEditor().getText(), txtmeasurement.getText(),combomaterialtype.getEditor().getText(), 
                    comboquality.getEditor().getText(), Integer.parseInt(txtqty.getText()), datepick.getValue().format(DateTimeFormatter.ISO_DATE)));
                    
            list.add(new tblMeasurementData(txtsuitid.getText(),combosuittype.getEditor().getText(),combomaterialtype.getEditor().getText(),
                    txtmeasurement.getText(),Integer.parseInt(txtqty.getText()), datepick.getValue().format(DateTimeFormatter.ISO_DATE)));
            tblsuit.setItems(list);
            new SuitOrderController().saveSuitOrder(new SuitOrder(txtsuitid.getText(),txtordernumber.getText()));
            clearFields();
            loadSuitID();
        }
        
        
    }
    
    private void initiateTable(){
        colSuitID.setCellValueFactory(new PropertyValueFactory<>("suitnumber"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colMaterial.setCellValueFactory(new PropertyValueFactory<>("material"));
        colMeasurement.setCellValueFactory(new PropertyValueFactory<>("measurement"));
        colDeliverydate.setCellValueFactory(new PropertyValueFactory<>("deliverydate"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
      
       
        
        
    }

    
    private void loadSuitID() {
        txtsuitid.setText(new SuitController().setSuitID());
    }
    
    private void clearFields(){
        combosuittype.getEditor().setText(null);
        combomaterialtype.getEditor().setText(null);
       
        datepick.getEditor().setText(null);
        txtqty.setText(null);
      
        
    }

    @FXML
    private void cleartxtMeasurement(ActionEvent event) {
        txtmeasurement.setText(null);
    }

    @FXML
    private void setDatatToFields(MouseEvent event) {
        txtsuitid.setText(tblsuit.getSelectionModel().getSelectedItem().getSuitnumber());
        combosuittype.getEditor().setText(tblsuit.getSelectionModel().getSelectedItem().getType());
        combomaterialtype.getEditor().setText(tblsuit.getSelectionModel().getSelectedItem().getMaterial());
        txtmeasurement.setText(tblsuit.getSelectionModel().getSelectedItem().getMeasurement());
        datepick.getEditor().setText(tblsuit.getSelectionModel().getSelectedItem().getDeliverydate());
        txtqty.setText(String.valueOf(tblsuit.getSelectionModel().getSelectedItem().getQty()));
        
        
        
    }

    @FXML
    private void updateSuitTable(ActionEvent event) {
        new SuitController().updateSuit(new Suit(txtsuitid.getText(),combosuittype.getEditor().getText(),
                combomaterialtype.getEditor().getText(),comboquality.getEditor().getText(),
                txtmeasurement.getText(),Integer.parseInt(txtqty.getText()), datepick.getValue().format(DateTimeFormatter.ISO_DATE),0));
        tblsuit.getItems().removeAll(tblsuit.getSelectionModel().getSelectedItem());
        list.add(new tblMeasurementData(txtsuitid.getText(),combosuittype.getEditor().getText(),combomaterialtype.getEditor().getText(),
                txtmeasurement.getText(),Integer.parseInt(txtqty.getText()),datepick.getValue().format(DateTimeFormatter.ISO_DATE)));
        tblsuit.setItems(list);
        clearFields();
        loadSuitID();
    }

    @FXML
    private void autoSearchType(KeyEvent event) {
        combosuittype.setItems(new SuitTypeController().autoLoad(combosuittype.getEditor().getText()));
        combosuittype.show();
    }

    @FXML
    private void autoLoadMaterial(KeyEvent event) {
        combomaterialtype.setItems(new MaterialController().autoloadMaterial(combomaterialtype.getEditor().getText()));
        combomaterialtype.show();
    }

    @FXML
    private void numbersOnly(KeyEvent event) {
        TextKeyHandling.onlyNumbers(event);
    }

    @FXML
    private void loadLoyalCustomer(ActionEvent event) throws IOException {
        new OrdersController().deleteOrder(txtordernumber.getText());
        Parent root=FXMLLoader.load(Main.class.getResource("views/loyalcustomer.fxml"));
        Scene scene=new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

  
    @FXML
    private void setDate(ActionEvent event) {
        if(datepick.getValue()!=null){
            String local=datepick.getValue().format(DateTimeFormatter.ISO_DATE);
            datepick.getEditor().setText(local);
        }else{
            datepick.getEditor().setText("YY/MM/DD");
        }
    }
    
  

   
    
    
   
    
    
   

     }

    

    

    

    
    
    
       
    

    

    
        
    
    
    
    
    
        
        
    

