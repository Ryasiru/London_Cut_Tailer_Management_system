
package londoncut.models.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import londoncut.db.DBConnection;
import londoncut.models.Suit;


public class SuitController {
    
    public String setSuitID(){
        String sql="SELECT suitnumber FROM suit ORDER BY suitnumber DESC LIMIT 1;";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            String id=null;
            while(result.next()){
                id=result.getString("suitnumber");
            }
            
            if(id!=null){
                String[] arr=id.split("S");
                int temp=Integer.parseInt(arr[1]);
                temp++;
                
                return temp<10?"S00"+temp:temp<100?"S0"+temp:"S"+temp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SuitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "S001";
    }
    
    public void saveSuit(Suit suit) throws SQLException{
        String sql="INSERT INTO suit VALUES (?,?,?,?,?,?,?,?);";
        Connection con=DBConnection.getInstance().getConnection();
        con.setAutoCommit(false);
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,suit.getSuitnumber());
            pstm.setString(2,suit.getType());
            pstm.setString(3,suit.getMaterial());
            pstm.setString(4,suit.getQuality());
            pstm.setString(5,suit.getMeasurement());
            pstm.setInt(6,suit.getQty());
            pstm.setString(7,suit.getDeliverydate());
            pstm.setInt(8,suit.getPrice());
            
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            con.rollback();
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        con.setAutoCommit(true);
    
    }
    
    public void updateSuit(Suit suit){
        String sql="UPDATE suit SET type=? , material=? , quality=? , measurement=? , qty=? , deliverydate=? ,price=? WHERE suitnumber=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,suit.getType());
            pstm.setString(2,suit.getMaterial());
            pstm.setString(3,suit.getQuality());
            pstm.setString(4,suit.getMeasurement());
            pstm.setInt(5,suit.getQty());
            pstm.setString(6,suit.getDeliverydate());
            pstm.setInt(7,suit.getPrice());
            pstm.setString(8,suit.getSuitnumber());
            
            
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SuitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void updateSuitPrice(String suitnumber,int qty,double price){
        double total=qty*price;
        String sql="UPDATE suit SET price=? WHERE suitnumber=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setDouble(1, total);
            pstm.setString(2, suitnumber);
            
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SuitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteSuit(String suitid){
        String sql="DELETE FROM suit WHERE suitnumber=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,suitid);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SuitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
