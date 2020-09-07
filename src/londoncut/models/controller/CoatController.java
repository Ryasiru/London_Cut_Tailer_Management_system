
package londoncut.models.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import londoncut.db.DBConnection;
import londoncut.models.Coat;


public class CoatController {
    
    static String selectedcoatnumber=null;
    
    public void saveCoat(Coat coat){
        String sql="INSERT INTO coat VALUES(?,?,?,?)";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,coat.getCoatnumber());
            pstm.setString(2,coat.getCoatcolour());
            pstm.setInt(3,coat.getPrice());
            pstm.setString(4,coat.getStatus());
            
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CoatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String setCoatNumber(){
        String sql = "SELECT coatnumber FROM coat ORDER BY coatnumber DESC LIMIT 1;";
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            String id = null;
            while (result.next()) {
                id = result.getString("coatnumber");
            }
            if (id != null) {
                String[] arr = id.split("CR");
                int temp = Integer.parseInt(arr[1]);
                temp++;

                return temp < 10 ? "CR00" + temp : temp < 100 ? "CR0" + temp : "CR" + temp;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "CR001";
    }
    
    public void deleteCoat(String data){
        String sql="DELETE FROM coat WHERE coatnumber=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, data);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CoatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateCoat(String price,String status,String coatnumber){
        String sql="UPDATE coat SET rentprice=? , status=? WHERE coatnumber=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setDouble(1,Double.parseDouble(price));
            pstm.setString(2,status);
            pstm.setString(3,coatnumber);
            pstm.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(CoatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getSelectedCoatNumber(String number){
        this.selectedcoatnumber=number;
    }
    
    public String setSelectedCoatNumber(){
        return selectedcoatnumber;
    }
    
    
}
