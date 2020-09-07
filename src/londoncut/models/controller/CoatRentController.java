
package londoncut.models.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import londoncut.db.DBConnection;
import londoncut.models.CoatRent;
import londoncut.tables.tblReturnCoat;


public class CoatRentController {

    public void saveCoatRent(CoatRent rent){
        String sql="INSERT INTO rentcoat(ordernumber,nic,customername,contact,coatnumber,rentdate,returndate,rentprice) VALUES (?,?,?,?,?,?,?,?)";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, rent.getOrdernumber());
            pstm.setString(2, rent.getNic());
            pstm.setString(3, rent.getCustomername());
            pstm.setInt(4, rent.getContact());
            pstm.setString(5, rent.getCoatnumber());
            pstm.setString(6, rent.getRentdate());
            pstm.setString(7, rent.getReturndate());
            pstm.setDouble(8, rent.getRentprice());
            
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CoatRentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateRentCoat(String data){
        String sql="UPDATE coat SET status='Rent' WHERE coatnumber=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, data);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CoatRentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateRentDetails(String nic,String date,String coatnumber,String rentdate,Double price){
        String sql="UPDATE rentcoat SET returndate=? , rentprice=? WHERE nic=? AND rentdate=? AND coatnumber=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, rentdate);
            pstm.setDouble(2, price);
            pstm.setString(3, nic);
            pstm.setString(4, date);
            pstm.setString(5, coatnumber);
            
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CoatRentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    public void deleteCoatRent(String nic ,String date,String coatnumber){
        String sql="DELETE FROM rentcoat WHERE nic=? AND coatnumber=? AND rentdate=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, nic);
            pstm.setString(2, coatnumber);
            pstm.setString(3, date);
            
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CoatRentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void updateCoatStatus(String data){
        String sql="UPDATE coat SET status='Available' WHERE coatnumber='"+data+"'";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CoatRentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
