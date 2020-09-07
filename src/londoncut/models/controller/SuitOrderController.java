
package londoncut.models.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import londoncut.db.DBConnection;
import londoncut.models.SuitOrder;


public class SuitOrderController {
    
    public void saveSuitOrder(SuitOrder data){
        String sql="INSERT INTO suitorder VALUES(?,?)";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,data.getSuitnumber());
            pstm.setString(2, data.getOrdernumber());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SuitOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void deleteSuitOrder(String suitid){
        String sql="DELETE FROM suitorder WHERE suitnumber=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,suitid);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SuitOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
