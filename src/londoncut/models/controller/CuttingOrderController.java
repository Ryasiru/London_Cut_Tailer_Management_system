
package londoncut.models.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import londoncut.db.DBConnection;
import londoncut.models.CuttingOrder;


public class CuttingOrderController {
    
    public void saveCuttingOrder(CuttingOrder order){
        String sql="INSERT INTO cuttingorder(ordernumber,suitnumber,type,measurement,material,quality,qty,deliverydate) VALUES (?,?,?,?,?,?,?,?)";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,order.getOrdernumber());
            pstm.setString(2,order.getSuitnumber());
            pstm.setString(3,order.getType());
            pstm.setString(4,order.getMeasurement());
            pstm.setString(5,order.getMaterial());
            pstm.setString(6,order.getQuality());
            pstm.setInt(7,order.getQty());
            pstm.setString(8,order.getDeliverydate());
            
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CuttingOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void deleteCuttingOrder(String data){
        String sql="DELETE FROM cuttingorder WHERE suitnumber=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,data);
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CuttingOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  
}
