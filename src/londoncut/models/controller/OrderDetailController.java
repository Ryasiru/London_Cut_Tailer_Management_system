
package londoncut.models.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import londoncut.db.DBConnection;
import londoncut.models.OrderDetail;


public class OrderDetailController {
    
    public void saveOrderDetail(OrderDetail orderdetail){
        String sql="INSERT INTO orderdetail VALUES(?,?);";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,orderdetail.getOrdernumber());
            pstm.setString(2,orderdetail.getCustomerid());
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
