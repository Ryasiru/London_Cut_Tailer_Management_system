
package londoncut.models.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import londoncut.db.DBConnection;
import londoncut.models.SellProduct;


public class SellProductController {
    
    public void saveSellProduct(SellProduct sp){
        String sql="INSERT INTO sellproduct(ordernumber,productname,qty,price) VALUES(?,?,?,?)";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, sp.getOrdernumber());
            pstm.setString(2, sp.getProductname());
            pstm.setInt(3, sp.getQty());
            pstm.setInt(4, sp.getPrice());
            
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SellProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    public void deleteOrder(String ordernumber,String name){
        String sql="DELETE FROM sellproduct WHERE ordernumber=? AND productname=? ";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, ordernumber);
            pstm.setString(2, name);
            
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SellProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
