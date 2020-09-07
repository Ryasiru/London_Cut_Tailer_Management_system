package londoncut.tables.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import londoncut.db.DBConnection;
import londoncut.tables.tblCustomerPayment;

public class tblCustomerPaymentController {

    public ObservableList<tblCustomerPayment> loadTable() {
        ObservableList<tblCustomerPayment> list = FXCollections.observableArrayList();
        String sql = "SELECT p.ordernumber,c.customerid,c.fname,c.lname,p.paymentdate FROM payment p,customer c,orderdetail od "
                + "WHERE p.ordernumber=od.ordernumber AND p.payment<p.total AND od.customerid=c.customerid";

        Connection con = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                list.add(new tblCustomerPayment(result.getString("ordernumber"),
                        result.getString("customerid"),
                        result.getString("fname"),
                        result.getString("lname"),
                        result.getString("paymentdate")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblCustomerPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public ObservableList<tblCustomerPayment> autoLoad(String data) throws SQLException {
        ObservableList<tblCustomerPayment> list = FXCollections.observableArrayList();
        String sql = "SELECT p.ordernumber , c.customerid , fname , lname , paymentdate FROM orderdetail od , customer c , payment p "
                + "WHERE p.total>p.payment AND p.ordernumber=od.ordernumber AND od.customerid=c.customerid AND ("
                + "p.ordernumber LIKE '%" + data + "%' OR c.customerid LIKE '%" + data + "%' OR fname LIKE '%" + data + "%' OR lname LIKE '%" + data + "%' "
                + "OR paymentdate LIKE '%" + data + "%' )";
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
            list.add(new tblCustomerPayment(result.getString("ordernumber"),
                    result.getString("customerid"),
                    result.getString("fname"),
                    result.getString("lname"),
                    result.getString("paymentdate")));
        }

        return list;
    }
}
