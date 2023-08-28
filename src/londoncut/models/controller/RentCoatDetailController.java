
package londoncut.models.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import londoncut.db.DBConnection;
import londoncut.models.RentCoatDetail;


public class RentCoatDetailController {
    
    public void addRentCoat(RentCoatDetail coat ){
        String sql="INSERT INTO rentcoatdetail(coatnumber,rentdate,returndate,rentprice,fine) VALUES(?,?,?,?,?)";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, coat.getCoatnumeber());
            pstm.setString(2, coat.getRentdate());
            pstm.setString(3, coat.getReturndate());
            pstm.setDouble(4, coat.getRentprice());
            pstm.setDouble(5, coat.getFine());
            
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(RentCoatDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateRentDetails(String returndate,double rentprice,String coatnumber,String rentdate){
        String sql="UPDATE rentcoatdetail SET returndate=? , rentprice=? WHERE coatnumber=? AND rentdate=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, returndate);
            pstm.setDouble(2, rentprice);
            pstm.setString(3, coatnumber);
            pstm.setString(4, rentdate);
           
            
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CoatRentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setFine(Double fine,String coatnumber,String returndate){
        String sql="UPDATE rentcoatdetail SET fine=? WHERE coatnumber=? AND returndate=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setDouble(1, fine);
            pstm.setString(2, coatnumber);
            pstm.setString(3, returndate);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RentCoatDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    
    public void deleteRent(String coatnumber,String rdate){
        String sql="DELETE FROM rentcoatdetail WHERE coatnumber=? AND rentdate=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, coatnumber);
            pstm.setString(2, rdate);
            
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RentCoatDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
