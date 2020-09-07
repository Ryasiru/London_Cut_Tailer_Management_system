/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import londoncut.models.CoatRent;
import londoncut.models.Orders;
import londoncut.models.RentCoatDetail;
import londoncut.models.controller.CoatRentController;
import londoncut.models.controller.CustomerController;
import londoncut.models.controller.OrdersController;
import londoncut.models.controller.RentCoatDetailController;
import londoncut.tables.controller.tblCoatRentController;
import londoncut.tables.controller.tblUpdateCoatController;
import londoncut.tables.tblCoatRent;
import londoncut.tables.tblCoatRentManage;
import londoncut.tables.tblUpdateCoat;
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
public class RentcoatController implements Initializable {

    @FXML
    private JFXButton btnhome;
    @FXML
    private AnchorPane rentcoatPane;
    @FXML
    private JFXTextField txtcustomername;
    @FXML
    private JFXTextField txtcontact;
    @FXML
    private JFXTextField txtnic;
    @FXML
    private JFXTextField txtsearch;
    @FXML
    private TableView<tblCoatRent> tblcoatrent;
    @FXML
    private TableColumn<tblCoatRent, String> colNumber;
    @FXML
    private TableColumn<tblCoatRent, String> colColour;
    @FXML
    private TableColumn<tblCoatRent, Double> colPrice;
    @FXML
    private TextField txtcoatnumber;
    @FXML
    private TextField txtprice;
    @FXML
    private TextField txttotal;

    @FXML
    private JFXButton btnupdate;
    @FXML
    private TextField txtupdateprice;

    @FXML
    private JFXButton btndelete;
    @FXML
    private JFXButton btnadd;

    static LocalDate localDate;

    private ObservableList<tblCoatRentManage> list = FXCollections.observableArrayList();
    @FXML
    private TableView<tblCoatRentManage> tblcoatrentmanage;
    @FXML
    private TableColumn<tblCoatRentManage, String> colCoatNumber;
    @FXML
    private TableColumn<tblCoatRentManage, String> colReturn;
    @FXML
    private TextField txtnumber;
    @FXML
    private TableColumn<tblCoatRentManage, Double> colCoatPrice;
    @FXML
    private JFXDatePicker returndate;
    @FXML
    private JFXDatePicker updatereturndate;
    @FXML
    private TextField txtordernumber;
    @FXML
    private JFXButton btnprint;

    @FXML
    private void loadHome(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(1000));
        ft.setNode(rentcoatPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        initiateTable();
        tblcoatrent.setItems(new tblCoatRentController().loadData());
        Date date = new Date();
        Instant instant = date.toInstant();
        localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        txtordernumber.setText(new OrdersController().setOrdernumber());
        try {
            new OrdersController().saveOrder(new Orders(txtordernumber.getText(), localDate.toString()));
        } catch (SQLException ex) {
            Logger.getLogger(RentcoatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initiateTable() {
        colNumber.setCellValueFactory(new PropertyValueFactory<>("coatnumber"));
        colColour.setCellValueFactory(new PropertyValueFactory<>("coatcolour"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colCoatNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        colReturn.setCellValueFactory(new PropertyValueFactory<>("returndate"));
        colCoatPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    private void loadFields(MouseEvent event) {
        txtcoatnumber.setText(tblcoatrent.getSelectionModel().getSelectedItem().getCoatnumber());
        txtprice.setText(String.valueOf(tblcoatrent.getSelectionModel().getSelectedItem().getPrice()));
    }

    @FXML
    private void autoSearch(KeyEvent event) {
        tblcoatrent.setItems(new tblCoatRentController().autoLoad(txtsearch.getText()));
    }

    @FXML
    private void addCoatRent(ActionEvent event) {
        if (txtnic.getText().isEmpty() || txtcoatnumber.getText().isEmpty() || txtprice.getText().isEmpty() || returndate.getValue().format(DateTimeFormatter.ISO_DATE).isEmpty()) {
            MessageAlert.ShowMessage("Missing Data....", "Error", Alert.AlertType.ERROR);

        } else {

            new CoatRentController().saveCoatRent(new CoatRent(txtordernumber.getText(),
                    txtnic.getText(), txtcustomername.getText(), Integer.parseInt(txtcontact.getText()),
                    txtcoatnumber.getText(), localDate.toString(), returndate.getValue().format(DateTimeFormatter.ISO_DATE), Double.parseDouble(txtprice.getText())));
            new RentCoatDetailController().addRentCoat(new RentCoatDetail(txtcoatnumber.getText(), localDate.toString(), returndate.getValue().format(DateTimeFormatter.ISO_DATE),
                    Double.parseDouble(txtprice.getText()), 0));
            new CoatRentController().updateRentCoat(txtcoatnumber.getText());
            list.add(new tblCoatRentManage(txtcoatnumber.getText(), returndate.getValue().format(DateTimeFormatter.ISO_DATE), Double.parseDouble(txtprice.getText())));
            tblcoatrentmanage.setItems(list);
            clearFields();
            tblcoatrent.setItems(new tblCoatRentController().loadData());

            getTotal();

        }
    }

    private void clearFields() {
        txtcoatnumber.setText(null);
        txtprice.setText(null);
        returndate.getEditor().setText("YY/MM/DD");
    }

    @FXML
    private void loadToFields(MouseEvent event) {
        txtnumber.setText(tblcoatrentmanage.getSelectionModel().getSelectedItem().getNumber());
        updatereturndate.getEditor().setText(tblcoatrentmanage.getSelectionModel().getSelectedItem().getReturndate());
        txtupdateprice.setText(String.valueOf(tblcoatrentmanage.getSelectionModel().getSelectedItem().getPrice()));
    }

    @FXML
    private void updateRentDetail(ActionEvent event) {
        if (tblcoatrentmanage.getSelectionModel().isEmpty() || txtupdateprice.getText().isEmpty()
                || updatereturndate.getValue().format(DateTimeFormatter.ISO_DATE).isEmpty()) {
            MessageAlert.ShowMessage("Missing Data in Update Information", "Error", Alert.AlertType.ERROR);
        } else {
            new RentCoatDetailController().updateRentDetails(updatereturndate.getValue().format(DateTimeFormatter.ISO_DATE), Double.parseDouble(txtupdateprice.getText()),
                    tblcoatrentmanage.getSelectionModel().getSelectedItem().getNumber(), localDate.toString());
            new CoatRentController().updateRentDetails(txtnic.getText(), localDate.toString(), txtnumber.getText(),
                    updatereturndate.getValue().format(DateTimeFormatter.ISO_DATE), Double.parseDouble(txtupdateprice.getText()));

            list.add(new tblCoatRentManage(txtnumber.getText(), updatereturndate.getValue().format(DateTimeFormatter.ISO_DATE), Double.parseDouble(txtupdateprice.getText())));
            tblcoatrentmanage.getItems().removeAll(tblcoatrentmanage.getSelectionModel().getSelectedItem());
            tblcoatrentmanage.setItems(list);
            txtnumber.setText(null);
            txtupdateprice.setText(null);
            updatereturndate.getEditor().setText(null);
            getTotal();

        }

    }

    private void getTotal() {
        int total = 0;
        TableColumn<tblCoatRentManage, Double> column = colCoatPrice;
        List<Double> list = new ArrayList();
        for (tblCoatRentManage item : tblcoatrentmanage.getItems()) {
            list.add(column.getCellObservableValue(item).getValue());
        }
        for (int i = 0; i < tblcoatrentmanage.getItems().size(); i++) {
            total += list.get(i);

        }
        txttotal.setText(String.valueOf(total));
    }

    @FXML
    private void deleteData(ActionEvent event) {
        if (tblcoatrentmanage.getSelectionModel().isEmpty()) {
            MessageAlert.ShowMessage("No Coat is Selected", "Alert", Alert.AlertType.INFORMATION);
        } else {
            new CoatRentController().updateCoatStatus(tblcoatrentmanage.getSelectionModel().getSelectedItem().getNumber());
            new CoatRentController().deleteCoatRent(txtnic.getText(), localDate.toString(),
                    tblcoatrentmanage.getSelectionModel().getSelectedItem().getNumber());
            tblcoatrentmanage.getItems().removeAll(tblcoatrentmanage.getSelectionModel().getSelectedItem());
            tblcoatrent.setItems(new tblCoatRentController().loadData());
            getTotal();
        }

    }

    @FXML
    private void charaterOnly(KeyEvent event) {
        TextKeyHandling.onlyCharacters(event);
    }

    @FXML
    private void numberOnly(KeyEvent event) {
        TextKeyHandling.onlyNumbers(event);
    }

    @FXML
    private void setReturnDate(ActionEvent event) {
        if (returndate.getValue() != null) {
            String local = returndate.getValue().format(DateTimeFormatter.ISO_DATE);
            returndate.getEditor().setText(local);
        } else {
            returndate.getEditor().setText("YY/MM/DD");
        }

    }

    @FXML
    private void setUpdateReturnDate(ActionEvent event) {
        if (updatereturndate.getValue() != null) {
            String local = updatereturndate.getValue().format(DateTimeFormatter.ISO_DATE);
            updatereturndate.getEditor().setText(local);
        } else {
            updatereturndate.getEditor().setText("YY/MM/DD");
        }

    }

    private void printCoatRent() throws FileNotFoundException, JRException {
        InputStream in = new FileInputStream("E:\\JavJ\\londoncut\\src\\londoncut\\report\\CoatRent.jrxml");
        HashMap para = new HashMap();
        para.put("ordernumber", txtordernumber.getText());
        para.put("name", txtcustomername.getText());
        para.put("total", txttotal.getText());
        para.put("nic", txtnic.getText());
        JasperReport jr = JasperCompileManager.compileReport(in);
        JasperPrint jp = JasperFillManager.fillReport(jr, para, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jp, false);

    }

    @FXML
    private void printReport(ActionEvent event) throws FileNotFoundException, JRException, SQLException {
        printCoatRent();
        txtordernumber.setText(new OrdersController().setOrdernumber());
        new OrdersController().saveOrder(new Orders(txtordernumber.getText(), localDate.toString()));
        txtcustomername.setText(null);
        txtcontact.setText(null);
        txtnic.setText(null);
        returndate.getEditor().setText("YY/mm/DD");
        tblcoatrentmanage.getItems().clear();

    }

}
