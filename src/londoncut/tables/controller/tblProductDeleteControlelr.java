
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
import londoncut.tables.tblProductDelete;


public class tblProductDeleteControlelr {
    
    public ObservableList<tblProductDelete > loadData(){
        ObservableList<tblProductDelete>list=FXCollections.observableArrayList();
        String sql="SELECT * FROM product";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(new tblProductDelete(result.getString("productid"),
                        result.getString("productname"),
                        result.getInt("unitprice"),
                        result.getInt("qty"),
                        result.getString("purchaseddate")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblProductDeleteControlelr.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public ObservableList<tblProductDelete> autoLoad(String data){
         ObservableList<tblProductDelete> list=FXCollections.observableArrayList();
        String sql="SELECT * FROM product WHERE productid LIKE '%"+data+"%' OR productname LIKE '%"+data+"%' OR unitprice LIKE '%"
                +data+"%' OR qty LIKE '%"+data+"%' OR purchaseddate LIKE '%"+data+"%'";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
           
            while(result.next()){
                list.add(new tblProductDelete(result.getString("productid"),
                        result.getString("productname"),
                        result.getInt("unitprice"), 
                        result.getInt("qty"),
                        result.getString("purchaseddate")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Measurement_3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
