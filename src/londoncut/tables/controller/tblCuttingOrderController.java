
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
import londoncut.tables.tblCuttingOrder;


public class tblCuttingOrderController {
    
    public ObservableList<tblCuttingOrder > loadData(){
        ObservableList<tblCuttingOrder> list=FXCollections.observableArrayList();
        String sql="SELECT * FROM cuttingorder ORDER BY deliverydate";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(new tblCuttingOrder(result.getString("ordernumber"),
                        result.getString("suitnumber"),
                        result.getString("type"),
                        result.getString("measurement"),
                        result.getString("material"),
                        result.getString("quality"),
                        result.getInt("qty"),
                        result.getString("deliverydate")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblCuttingOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return list;
    }
    
    public ObservableList<tblCuttingOrder > autoLoad(String data){
        ObservableList<tblCuttingOrder> list=FXCollections.observableArrayList();
        String sql="SELECT * FROM cuttingorder WHERE ordernumber LIKE '%"+data+"%' ORDER BY deliverydate";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(new tblCuttingOrder(result.getString("ordernumber"),
                        result.getString("suitnumber"),
                        result.getString("type"),
                        result.getString("measurement"),
                        result.getString("material"),
                        result.getString("quality"),
                        result.getInt("qty"),
                        result.getString("deliverydate")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblCuttingOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return list;
    }
    
}
