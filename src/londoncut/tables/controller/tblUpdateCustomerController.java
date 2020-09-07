
package londoncut.tables.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import londoncut.controller.Measurement_3Controller;
import londoncut.db.DBConnection;
import londoncut.tables.tblDeleteCustomer;
import londoncut.tables.tblUpdateCustomer;


public class tblUpdateCustomerController {
    
    public ObservableList<tblUpdateCustomer > loadTable(){
        ObservableList<tblUpdateCustomer> list=FXCollections.observableArrayList();
        String sql="SELECT * FROM customer";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(new tblUpdateCustomer(
                        result.getString("customerid"),
                        result.getString("fname"),
                        result.getString("lname"),
                        result.getInt("contact"),
                        result.getString("nic")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblUpdateCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ObservableList<tblUpdateCustomer> autoLoad(String data){
        ObservableList<tblUpdateCustomer> list=FXCollections.observableArrayList();
        String sql="SELECT * FROM customer WHERE customerid LIKE '%"+data+"%' OR fname LIKE '%"+data+"%' OR lname LIKE '%"
                +data+"%' OR contact LIKE '%"+data+"%' OR nic LIKE '%"+data+"%'";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
           
            while(result.next()){
                list.add(new tblUpdateCustomer(
                        result.getString("customerid"),
                        result.getString("fname"),
                        result.getString("lname"),
                        result.getInt("contact"), 
                        result.getString("nic")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Measurement_3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
