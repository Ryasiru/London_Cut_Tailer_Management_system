package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
import londoncut.models.Payment;
import londoncut.models.controller.OrdersController;
import londoncut.models.controller.PaymentController;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import static org.apache.velocity.texen.util.FileUtil.file;
import static org.apache.velocity.texen.util.FileUtil.file;
//import sun.plugin.javascript.navig.Anchor;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class Measurement_4Controller implements Initializable {

    @FXML
    private AnchorPane measurementpaymentPane;
    @FXML
    private JFXButton btnhome;
    @FXML
    private JFXTextField txtadvance;

    @FXML
    private TextField txtfname;
    @FXML
    private TextField txtlname;
    @FXML
    private TextField txtordernumber;
    @FXML
    private TextField txtorderdate;
    @FXML
    private ToggleGroup paymenttype;

    @FXML
    private JFXRadioButton radioFull;
    @FXML
    private JFXRadioButton radioAdvance;

    private double total;
    static double suittot;
    static double renttot;
    static int producttot;

    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtbill;
    @FXML
    private Button btnsuit;
    @FXML
    private Button btncoat;
    @FXML
    private Button btnproduct;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setOrderData();
        try {
            loadPane();
        } catch (IOException ex) {
            Logger.getLogger(Measurement_4Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        txtbill.setText(String.valueOf(total));

    }

    @FXML
    private void backHome(ActionEvent event) throws IOException, JRException {

        if (radioFull.isSelected()) {
            new PaymentController().savePayment(new Payment(txtordernumber.getText(), txtorderdate.getText(),
                    Double.parseDouble(txtbill.getText()), Double.parseDouble(txtbill.getText())));
            printReport();
            loadWindow(event);
        } else if (radioAdvance.isSelected()) {
            if (txtadvance.getText().isEmpty()) {
                MessageAlert.ShowMessage("No Advance has Done...", "Payment Error", Alert.AlertType.ERROR);
            } else {
                new PaymentController().savePayment(new Payment(txtordernumber.getText(), txtorderdate.getText(),
                        Double.parseDouble(txtadvance.getText()), Double.parseDouble(txtbill.getText())));
                printReport();
                loadWindow(event);
            }

        } else {
            MessageAlert.ShowMessage("No Payments has Done...", "Payment Error", Alert.AlertType.ERROR);
        }

    }

    private void setOrderData() {
        txtordernumber.setText(new OrdersController().setSelectOrder());
        String sql = "SELECT c.fname , c.lname , o.date FROM customer c , orders o , orderdetail od WHERE od.ordernumber='"
                + txtordernumber.getText() + "' AND od.ordernumber=o.ordernumber AND od.customerid=c.customerid;";
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                txtfname.setText(result.getString("fname"));
                txtlname.setText(result.getString("lname"));
                txtorderdate.setText(result.getString("date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Measurement_4Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadWindow(ActionEvent event) throws IOException {
        Parent next = FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
        Scene scene = new Scene(next);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    private void numberOnly(KeyEvent event) {
        TextKeyHandling.onlyNumbers(event);
    }

    @FXML
    private void enableTextadvance(ActionEvent event) {
        txtadvance.setEditable(true);
    }

    @FXML
    private void disableTextadvance(ActionEvent event) {
        txtadvance.setEditable(false);
    }

    private void printReport() throws JRException, FileNotFoundException {
        InputStream in = new FileInputStream("src/londoncut/report/Payment.jrxml");
        HashMap para = new HashMap();
        para.put("ordernumber", txtordernumber.getText());
        para.put("order", txtordernumber.getText());
        para.put("custname", txtfname.getText() + " " + txtlname.getText());
        para.put("total", txtbill.getText());
        JasperReport jr = JasperCompileManager.compileReport(in);
        JasperPrint jp = JasperFillManager.fillReport(jr, para, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jp, false);

    }

    private void loadPane() throws IOException {
        FXMLLoader parent = new FXMLLoader(Main.class.getResource("views/suitdetail.fxml"));
        AnchorPane pane = parent.load();
        root.getChildren().clear();
        root.getChildren().add(pane);
        SuitdetailController controller = parent.<SuitdetailController>getController();
        controller.setController(this);
    }

    public void setSuitTotal(double tot) {
        if (suittot == 0) {
            suittot = tot;
            total += suittot;
        } else if (tot >= suittot) {
            total += (tot - suittot);
            suittot = tot;
        } else {
            total -= (suittot - tot);
            suittot = tot;
        }
        txtbill.setText(String.valueOf(total));
    }

    public void setRentTotal(double tot) {
        if (renttot == 0) {
            renttot = tot;
            total += renttot;
        } else {
            total += tot;
        }
        txtbill.setText(String.valueOf(total));
    }
    
    public void setProductTotal(int tot) {
        if (producttot == 0) {
            producttot = tot;
            total += producttot;
        } else if (tot >= producttot) {
            total += (tot - producttot);
            producttot = tot;
        } else {
            total -= (producttot - tot);
            producttot = tot;
        }
        txtbill.setText(String.valueOf(total));
    }

    

    @FXML
    private void loadSuit(ActionEvent event) throws IOException {
        FXMLLoader parent = new FXMLLoader(Main.class.getResource("views/suitdetail.fxml"));
        AnchorPane pane = parent.load();
        root.getChildren().clear();
        root.getChildren().add(pane);
        SuitdetailController controller = parent.<SuitdetailController>getController();
        controller.setController(this);
    }

    @FXML
    private void loadCoat(ActionEvent event) throws IOException {
        FXMLLoader parent = new FXMLLoader(Main.class.getResource("views/rentcoat_2.fxml"));
        AnchorPane pane = parent.load();
        root.getChildren().clear();
        root.getChildren().add(pane);
        Rentcoat_2Controller controller = parent.<Rentcoat_2Controller>getController();
        controller.setController(this, txtorderdate.getText());
    }

    @FXML
    private void loadProducts(ActionEvent event) throws IOException {
        FXMLLoader parent = new FXMLLoader(Main.class.getResource("views/productsell.fxml"));
        AnchorPane pane = parent.load();
        root.getChildren().clear();
        root.getChildren().add(pane);
        ProductsellController  controller = parent.<ProductsellController>getController();
        controller.setController(this);
    }

}
