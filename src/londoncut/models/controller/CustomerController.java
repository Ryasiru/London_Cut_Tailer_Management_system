package londoncut.models.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import londoncut.db.DBConnection;
import londoncut.models.Customer;

public class CustomerController {

    static String selectedid = null;

    public boolean saveCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer VALUES (?,?,?,?,?)";

        Connection con = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, customer.getCustomerid());
            pstm.setString(2, customer.getFname());
            pstm.setString(3, customer.getLname());
            pstm.setInt(4, customer.getContact());
            pstm.setString(5, customer.getNic());

            return pstm.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public String setCustomerID() {
        String sql = "SELECT customerid FROM customer ORDER BY customerid DESC LIMIT 1;";
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            String id = null;
            while (result.next()) {
                id = result.getString("customerid");
            }
            if (id != null) {
                String[] arr = id.split("C");
                int temp = Integer.parseInt(arr[1]);
                temp++;

                return temp < 10 ? "C00" + temp : temp < 100 ? "C0" + temp : "C" + temp;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "C001";
    }
    
    public void deleteCustomer(String customerid){
            String sql="DELETE FROM customer WHERE customerid=?";
            Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,customerid);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public void updateCustomer(Customer customer){
        String sql="UPDATE customer SET fname=? , lname=? , contact=? , nic=? WHERE customerid=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,customer.getFname());
            pstm.setString(2,customer.getLname());
            pstm.setInt(3,customer.getContact());
            pstm.setString(4,customer.getNic());
            pstm.setString(5,customer.getCustomerid());
            
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }
    
    
    
    public void getSelectedCustomerid(String id) {
        this.selectedid = id;
    }

    public String setSelectedCustomerid() {
        return selectedid;
    }
}
