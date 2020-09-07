
package londoncut.models.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import londoncut.db.DBConnection;
import londoncut.models.Tailor;

public class TailorController {
    static String selectedid=null;
    public void saveTailor(Tailor tailor){
        String sql="INSERT INTO tailor VALUES(?,?,?,?,?,?,?)";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,tailor.getTailorid());
            pstm.setString(2,tailor.getFname());
            pstm.setString(3,tailor.getLname());
            pstm.setString(4,tailor.getAddress());
            pstm.setInt(5,tailor.getHome());
            pstm.setInt(6,tailor.getMobile());
            pstm.setString(7,tailor.getNic());
            
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TailorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String setTailorID(){
        String sql = "SELECT tailorid FROM tailor ORDER BY tailorid DESC LIMIT 1;";
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            String id = null;
            while (result.next()) {
                id = result.getString("tailorid");
            }
            if (id != null) {
                String[] arr = id.split("T");
                int temp = Integer.parseInt(arr[1]);
                temp++;

                return temp < 10 ? "T00" + temp : temp < 100 ? "T0" + temp : "T" + temp;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "T001";
    }
    
    public void deleteTailor(String id){
        String sql="DELETE FROM tailor WHERE tailorid=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TailorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateTailor(Tailor tailor){
        String sql="UPDATE tailor SET fname=? , lname=? , address=? , home=? , mobile=? WHERE tailorid=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,tailor.getFname());
            pstm.setString(2,tailor.getLname());
            pstm.setString(3,tailor.getAddress());
            pstm.setInt(4,tailor.getHome());
            pstm.setInt(5,tailor.getMobile());
            pstm.setString(6,tailor.getTailorid());
            
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(TailorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> loadSelectedDetail(String id){
        ArrayList<String>list=new ArrayList();
        String sql="SELECT fname,lname,address,home,mobile FROM tailor WHERE tailorid=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, id);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(result.getString("fname"));
                list.add(result.getString("lname"));
                list.add(result.getString("address"));
                list.add(result.getString("home"));
                list.add(result.getString("mobile"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TailorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public void getSelectedid(String id){
        this.selectedid=id;
    }
    
    public String setSelectedid(){
        return selectedid;
    }
    
}
