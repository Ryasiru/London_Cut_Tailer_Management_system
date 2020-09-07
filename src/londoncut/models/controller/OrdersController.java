
package londoncut.models.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import londoncut.db.DBConnection;
import londoncut.models.Orders;


public class OrdersController {
    static String selectedordernumber=null;
    
    public String setOrdernumber(){
        String sql="SELECT ordernumber FROM orders ORDER BY ordernumber DESC LIMIT 1;";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            String id=null;
            if(result.next()){
                id=result.getString("ordernumber");
            }
            if(id!=null){
                String[] arr=id.split("P");
                int temp=Integer.parseInt(arr[1]);
                temp++;
                
                return temp<10?"P00"+temp:temp<100?"P0"+temp:"P"+temp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "P001";
    }
    
    public void saveOrder(Orders order) throws SQLException{
        String sql="INSERT INTO orders VALUES(?,?);";
        Connection con=DBConnection.getInstance().getConnection();
        PreparedStatement pstm=con.prepareStatement(sql);
        pstm.setString(1,order.getOrdernumber());
        pstm.setString(2, order.getDate());
        pstm.executeUpdate();
    }
    
    public void deleteOrder(String ordernumber){
        String sql="DELETE FROM orders WHERE ordernumber=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, ordernumber);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrdersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getSelectedOrder(String data){
        this.selectedordernumber=data;
    }
    
    public String setSelectOrder(){
        return selectedordernumber;
    }
}
