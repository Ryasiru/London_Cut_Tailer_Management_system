package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import londoncut.db.DBConnection;
import londoncut.models.CoatRent;
import londoncut.models.RentCoatDetail;
import londoncut.models.controller.CoatRentController;
import londoncut.models.controller.CustomerController;
import londoncut.models.controller.OrdersController;
import londoncut.models.controller.RentCoatDetailController;
import londoncut.tables.tblRentCoat;
import londoncut.tables.tblRentCoatDetail;

public class Rentcoat_2Controller implements Initializable {

    @FXML
    private TableView<tblRentCoat> tblrentcoat;
    @FXML
    private TableColumn<tblRentCoat, String> colNumber;
    @FXML
    private TableColumn<tblRentCoat, Double> colPrice;
    @FXML
    private TableView<tblRentCoatDetail> tblrent;
    @FXML
    private TableColumn<tblRentCoatDetail, String> colNumberRent;
    private TextField txtreturndate;
    @FXML
    private TextField txtrentprice;
    
    @FXML
    private TextField txtupdaterentprice;
    @FXML
    private JFXButton btnadd;

    private Measurement_4Controller controller;

    private String fname;
    private String lname;
    private String nic;
    private int contact;
    private String date;
    @FXML
    private JFXButton btnupdate;
    @FXML
    private JFXDatePicker returndate;
    @FXML
    private JFXDatePicker updatereturn;
    @FXML
    private JFXButton btndelete;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTable();
        tblrentcoat.setItems(loadData());
        getCustomerDetail();
        tblrent.setItems(loadData_2());
    }

    private void initiateTable() {
        colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        colNumberRent.setCellValueFactory(new PropertyValueFactory<>("number"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private ObservableList<tblRentCoat> loadData() {
        ObservableList<tblRentCoat> list = FXCollections.observableArrayList();
        String sql = "SELECT coatnumber , price FROM coat WHERE status='Available'";
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                list.add(new tblRentCoat(result.getString("coatnumber"),
                        result.getDouble("price")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Rentcoat_2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    private ObservableList<tblRentCoatDetail> loadData_2() {
        ObservableList<tblRentCoatDetail> list = FXCollections.observableArrayList();
        String sql = "SELECT coatnumber FROM rentcoat WHERE ordernumber=?";
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, new OrdersController().setSelectOrder());
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                list.add(new tblRentCoatDetail(result.getString("coatnumber")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Rentcoat_2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    private void getCustomerDetail() {
        String sql = "SELECT fname,lname,nic,contact FROM customer WHERE customerid=?";
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, new CustomerController().setSelectedCustomerid());
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                this.fname = result.getString("fname");
                this.lname = result.getString("lname");
                this.nic = result.getString("nic");
                this.contact = result.getInt("contact");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Rentcoat_2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadFields(MouseEvent event) {
        txtrentprice.setText(String.valueOf(tblrentcoat.getSelectionModel().getSelectedItem().getPrice()));

    }

    @FXML
    private void addRentCoat(ActionEvent event) {
        new CoatRentController().saveCoatRent(new CoatRent(new OrdersController().setSelectOrder(),
                nic, fname + "" + lname, contact,
                tblrentcoat.getSelectionModel().getSelectedItem().getNumber(), date, returndate.getValue().format(DateTimeFormatter.ISO_DATE),
                Double.parseDouble(txtrentprice.getText())));
        new RentCoatDetailController().addRentCoat(new RentCoatDetail(tblrentcoat.getSelectionModel().getSelectedItem().getNumber(),
                date,  returndate.getValue().format(DateTimeFormatter.ISO_DATE),
                Double.parseDouble(txtrentprice.getText()), 0));
        new CoatRentController().updateRentCoat(tblrentcoat.getSelectionModel().getSelectedItem().getNumber());
        tblrentcoat.setItems(loadData());
        tblrent.setItems(loadData_2());
        controller.setRentTotal(Double.parseDouble(txtrentprice.getText()));
        txtrentprice.setText(null);
        returndate.getEditor().setText(null);

    }

    public void setController(Measurement_4Controller controller, String date) {
        this.controller = controller;
        this.date = date;
    }

    @FXML
    private void loadToFields(MouseEvent event) {
        String sql = "SELECT rentprice,returndate FROM rentcoat WHERE ordernumber=? AND coatnumber=?";
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, new OrdersController().setSelectOrder());
            pstm.setString(2, tblrent.getSelectionModel().getSelectedItem().getNumber());
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                txtupdaterentprice.setText(result.getString("rentprice"));
                updatereturn.getEditor().setText(result.getString("returndate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Rentcoat_2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateRentCoat(ActionEvent event) {
        double total=0;
        String oldprice="SELECT rentprice FROM rentcoat WHERE ordernumber=? AND coatnumber=?";
        String sql = "UPDATE rentcoat SET rentprice=? , returndate=? WHERE ordernumber=? AND coatnumber=?";
        String detail = "UPDATE rentcoatdetail SET rentprice=? , returndate=? WHERE rentdate=? AND coatnumber=?";
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            PreparedStatement pstmprice = con.prepareStatement(oldprice);
            PreparedStatement pstmdetail= con.prepareStatement(detail);
            pstmdetail.setDouble(1,Double.parseDouble(txtupdaterentprice.getText()));
            pstmdetail.setString(2,updatereturn.getValue().format(DateTimeFormatter.ISO_DATE));
            pstmdetail.setString(4,date);
            pstmdetail.setString(5,tblrent.getSelectionModel().getSelectedItem().getNumber());
            
            pstmprice.setString(1, new OrdersController().setSelectOrder());
            pstmprice.setString(2, tblrent.getSelectionModel().getSelectedItem().getNumber());
            pstm.setDouble(1, Double.parseDouble(txtupdaterentprice.getText()));
            pstm.setString(2, updatereturn.getValue().format(DateTimeFormatter.ISO_DATE));
            pstm.setString(3, new OrdersController().setSelectOrder());
            pstm.setString(4, tblrent.getSelectionModel().getSelectedItem().getNumber());
            ResultSet result=pstmprice.executeQuery();
            while(result.next()){
                total=result.getDouble("rentprice");
            }
            pstm.executeUpdate();
            pstmdetail.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Rentcoat_2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(total>=Double.parseDouble(txtupdaterentprice.getText())){
            double temp=total-Double.parseDouble(txtupdaterentprice.getText());
            controller.setRentTotal(-temp);
        }else{
            double temp=Double.parseDouble(txtupdaterentprice.getText())-total;
            controller.setRentTotal(temp);
        }
        txtupdaterentprice.setText(null);
        updatereturn.getEditor().setText("YY/mm/DD");
    }

    @FXML
    private void setDateReturn(ActionEvent event) {
        if(returndate.getValue()!=null){
            String local=returndate.getValue().format(DateTimeFormatter.ISO_DATE);
            returndate.getEditor().setText(local);
        }else{
            returndate.getEditor().setText("YY/MM/DD");
        }
    }

    @FXML
    private void setDateUpdate(ActionEvent event) {
        if(updatereturn.getValue()!=null){
            String local=updatereturn.getValue().format(DateTimeFormatter.ISO_DATE);
            updatereturn.getEditor().setText(local);
        }else{
            updatereturn.getEditor().setText("YY/MM/DD");
        }
    }

    @FXML
    private void deleteRentCoat(ActionEvent event) {
        new CoatRentController().deleteCoatRent(nic, date, tblrent.getSelectionModel().getSelectedItem().getNumber());
        new RentCoatDetailController().deleteRent(tblrent.getSelectionModel().getSelectedItem().getNumber(), date);
        new CoatRentController().updateCoatStatus(tblrent.getSelectionModel().getSelectedItem().getNumber());
        tblrentcoat.setItems(loadData());
    }
}
