
package londoncut.models.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import londoncut.db.DBConnection;
import londoncut.models.TailorSewWork;


public class TailorSewWorkController {
    
    static int tot=0;
    
    public void saveTailorSewWork(TailorSewWork t){
        String sql="INSERT INTO tailorsewwork() VALUES (?,?,?,?,?,?)";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,t.getOrdernumber());
            pstm.setString(2,t.getSuitnumber());
            pstm.setString(3,t.getType());
            pstm.setString(4,t.getMeasurement());
            pstm.setString(5,t.getDeliverydate());
            pstm.setInt(6,t.getQty());
            
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TailorSewWorkController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addTailorWork(String suitnumber,String tailorid,int qty,double price){
        String sql="INSERT INTO tailorwork(suitnumber,tailorid,qty,price) VALUES(?,?,?,?)";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,suitnumber);
            pstm.setString(2,tailorid);
            pstm.setInt(3,qty);
            pstm.setDouble(4,price);
            
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TailorSewWorkController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateQty(String id,int qty){
        int total=tot-qty;
        String sql="UPDATE tailorsewwork SET qty=? WHERE suitnumber=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setInt(1, total);
            pstm.setString(2, id);
            
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TailorSewWorkController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setTot(int qty){
        this.tot=qty;
    }
    
    public void updateSalary(String id,double payment){
        double salary=0;
        String sql="SELECT salary FROM tailor WHERE tailorid=? ";
        String update="UPDATE tailor SET salary=? WHERE tailorid=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            PreparedStatement pstmupdate=con.prepareStatement(update);
            pstm.setString(1, id);
            ResultSet result=pstm.executeQuery();
            if(result.next()){
                salary=result.getDouble("salary");
            }
            salary+=payment;
            pstmupdate.setDouble(1,salary);
            pstmupdate.setString(2, id);
            pstmupdate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TailorSewWorkController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
