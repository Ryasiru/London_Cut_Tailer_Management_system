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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import londoncut.Main;
import londoncut.alerts.MessageAlert;
import londoncut.db.DBConnection;
import londoncut.events.TextKeyHandling;
import londoncut.models.Orders;
import londoncut.models.SellProduct;
import londoncut.models.controller.OrdersController;
import londoncut.models.controller.ProductController;
import londoncut.models.controller.SellProductController;
import londoncut.tables.controller.tblSellProductController;
import londoncut.tables.tblSellProduct;
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
public class SellproductController implements Initializable {

    @FXML
    private JFXButton btnhome;
    @FXML
    private AnchorPane sellproductPane;
    @FXML
    private TextField txtorderid;
    @FXML
    private JFXComboBox<String> comboProduct;
    @FXML
    private JFXTextField txtprice;
    @FXML
    private JFXTextField txtqty;
    @FXML
    private TableView<tblSellProduct> tblsellproduct;

    @FXML
    private TableColumn<tblSellProduct, String> colName;
    @FXML
    private TableColumn<tblSellProduct, Integer> colQty;
    @FXML
    private TableColumn<tblSellProduct, Integer> colPrice;

   
    @FXML
    private JFXButton btnadd;

    static LocalDate localDate;
    @FXML
    private JFXButton btndelete;
    @FXML
    private TextField txttotal;
    @FXML
    private JFXButton btnprint;

    @FXML
    private void loadhome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation();
        txtorderid.setText(new OrdersController().setOrdernumber());
        initiateTable();
        Date date = new Date();
        Instant instant = date.toInstant();

        localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        try {
            new OrdersController().saveOrder(new Orders(txtorderid.getText(), localDate.toString()));
        } catch (SQLException ex) {
            Logger.getLogger(SellproductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        comboProduct.setItems(new ProductController().loadCombo(comboProduct.getEditor().getText()));

    }

    private void Animation() {
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(1000));
        ft.setNode(sellproductPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    private void initiateTable() {

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tblsellproduct.setItems(new tblSellProductController().loadData(txtorderid.getText()));
    }

    @FXML
    private void autoLoad(KeyEvent event) {
        comboProduct.setItems(new ProductController().loadCombo(comboProduct.getEditor().getText()));
        comboProduct.show();
        tblsellproduct.setItems(new tblSellProductController().loadData(txtorderid.getText()));
    }

    @FXML
    private void loadPrice(ActionEvent event) {
        txtprice.setText(new ProductController().getProductPrice(comboProduct.getEditor().getText()));
    }

    @FXML
    private void addSellProduct(ActionEvent event) throws SQLException {
        if (comboProduct.getEditor().getText().isEmpty() || txtprice.getText().isEmpty() || txtqty.getText().isEmpty()) {
            MessageAlert.ShowMessage("Missing Data...", "Error", Alert.AlertType.ERROR);
        } else {
            if (tblsellproduct.getItems().size() == 0) {
                int total = Integer.parseInt(txtprice.getText()) * Integer.parseInt(txtqty.getText());
                new SellProductController().saveSellProduct(new SellProduct(txtorderid.getText(), comboProduct.getEditor().getText(),
                        Integer.parseInt(txtqty.getText()), total));
                new ProductController().updateQty(comboProduct.getEditor().getText(), Integer.parseInt(txtqty.getText()));
                tblsellproduct.setItems(new tblSellProductController().loadData(txtorderid.getText()));
                getTotal();
                clearFields();
            } else {
                for (int i = 0; i < tblsellproduct.getItems().size(); i++) {
                    if (tblsellproduct.getItems().get(i).getName().equals(comboProduct.getEditor().getText())) {
                        int total = tblsellproduct.getItems().get(i).getPrice();
                        int qty = tblsellproduct.getItems().get(i).getQty();
                        total = total + Integer.parseInt(txtprice.getText()) * Integer.parseInt(txtqty.getText());
                        qty = qty + Integer.parseInt(txtqty.getText());
                        String sql = "UPDATE sellproduct SET qty=?,price=? WHERE ordernumber=?";
                        Connection con = DBConnection.getInstance().getConnection();
                        PreparedStatement pstm = con.prepareStatement(sql);
                        pstm.setInt(1, qty);
                        pstm.setInt(2, total);
                        pstm.setString(3, txtorderid.getText());
                        pstm.executeUpdate();
                        tblsellproduct.setItems(new tblSellProductController().loadData(txtorderid.getText()));
                        getTotal();
                        clearFields();

                    } else {
                        int total = Integer.parseInt(txtprice.getText()) * Integer.parseInt(txtqty.getText());
                        new SellProductController().saveSellProduct(new SellProduct(txtorderid.getText(), comboProduct.getEditor().getText(),
                                Integer.parseInt(txtqty.getText()), total));
                        new ProductController().updateQty(comboProduct.getEditor().getText(), Integer.parseInt(txtqty.getText()));
                        tblsellproduct.setItems(new tblSellProductController().loadData(txtorderid.getText()));
                        getTotal();
                        clearFields();
                    }
                }
            }

        }

    }

    @FXML
    private void deleteProductOrder(ActionEvent event) {
        if (tblsellproduct.getSelectionModel().isEmpty()) {
            MessageAlert.ShowMessage("No Product is Selected...", "Alert", Alert.AlertType.WARNING);
        } else {

            new SellProductController().deleteOrder(txtorderid.getText(), tblsellproduct.getSelectionModel().getSelectedItem().getName());
            new ProductController().setQty(tblsellproduct.getSelectionModel().getSelectedItem().getName(),
                    tblsellproduct.getSelectionModel().getSelectedItem().getQty());
            tblsellproduct.getItems().removeAll(tblsellproduct.getSelectionModel().getSelectedItem());
            getTotal();

        }
    }

    private void getTotal() {
        int total = 0;
        TableColumn<tblSellProduct, Integer> column = colPrice;
        List<Integer> list = new ArrayList();
        for (tblSellProduct item : tblsellproduct.getItems()) {
            list.add(column.getCellObservableValue(item).getValue());
        }
        for (int i = 0; i < tblsellproduct.getItems().size(); i++) {
            total += list.get(i);

        }
        txttotal.setText(String.valueOf(total));
    }

    @FXML
    private void numberOnly(KeyEvent event) {
        TextKeyHandling.onlyNumbers(event);
    }

    private void printProduct() throws FileNotFoundException, JRException {
        InputStream in = new FileInputStream("E:\\londoncut\\src\\londoncut\\report\\Product.jrxml");
        System.out.println(txtorderid.getText()+""+txttotal.getText());
        HashMap para = new HashMap();
        para.put("ordernumber", txtorderid.getText());
        para.put("total", txttotal.getText());
        JasperReport jr = JasperCompileManager.compileReport(in);
        JasperPrint jp = JasperFillManager.fillReport(jr, para, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jp, false);
    }

    @FXML
    private void printReport(ActionEvent event) throws FileNotFoundException, JRException, SQLException {
        printProduct();
        tblsellproduct.getItems().clear();
        comboProduct.getEditor().setText(null);
        txtprice.setText(null);
        txtqty.setText(null);
        txttotal.setText(null);

    }

    private void clearFields() {
        comboProduct.getEditor().setText(null);
        txtprice.setText(null);
        txtqty.setText(null);
    }

}
