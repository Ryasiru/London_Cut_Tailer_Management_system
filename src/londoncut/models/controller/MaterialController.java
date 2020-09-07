
package londoncut.models.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import londoncut.controller.Measurement_3Controller;
import londoncut.db.DBConnection;
import londoncut.models.Material;


public class MaterialController {
    
    static String selectedid=null;
    
    public ObservableList<String> loadMaterial(){
        String sql="SELECT materialnumber FROM material;";
        Connection con=DBConnection.getInstance().getConnection();
        ObservableList<String> list=FXCollections.observableArrayList();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(result.getString("materialnumber"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public String setMaterialNumber(){
        String sql = "SELECT materialnumber FROM material ORDER BY materialnumber DESC LIMIT 1;";
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            String id = null;
            while (result.next()) {
                id = result.getString("materialnumber");
            }
            if (id != null) {
                String[] arr = id.split("M");
                int temp = Integer.parseInt(arr[1]);
                temp++;

                return temp < 10 ? "M00" + temp : temp < 100 ? "M0" + temp : "M" + temp;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "M001";
    }
    
    public void saveMaterial(Material mat){
        String sql="INSERT INTO material VALUES(?,?,?,?)";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, mat.getMaterialnumber());
            pstm.setString(2, mat.getMaterial());
            pstm.setString(3, mat.getQuality());
            pstm.setDouble(4, mat.getQuantity());
            
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(MaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteMaterial(String id){
        String sql="DELETE FROM material WHERE materialnumber=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public ObservableList<String> loadQuality(String data){
        String sql="SELECT quality FROM material WHERE materialnumber='"+data+"';";
        Connection con=DBConnection.getInstance().getConnection();
        ObservableList<String> list=FXCollections.observableArrayList();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(result.getString("quality"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ObservableList<String> getQuality(){
        ObservableList<String> list=FXCollections.observableArrayList();
        String sql="SELECT quality FROM material";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(result.getString("quality"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }
    
    public ObservableList<String> autoloadMaterial(String data){
        ObservableList<String> list=FXCollections.observableArrayList();
        String sql="SELECT materialnumber FROM material WHERE materialnumber LIKE '%"+data+"%';";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(result.getString("materialnumber"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Measurement_3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
    
    public ArrayList<String> loadSelectedData(String id){
        ArrayList<String> list=new ArrayList();
        String sql="SELECT material , quality,quantity FROM material WHERE materialnumber=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, id);
            ResultSet result= pstm.executeQuery();
            while(result.next()){
                list.add(result.getString("material"));
                list.add(result.getString("quality"));
                list.add(result.getString("quantity"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public void updateMaterial(Material mat){
        String sql="UPDATE material SET material=? , quality=? , quantity=? WHERE materialnumber=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, mat.getMaterial());
            pstm.setString(2, mat.getQuality());
            pstm.setDouble(3, mat.getQuantity());
            pstm.setString(4, mat.getMaterialnumber());
            
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void updateQty(String material,String quality,double qty){
        double total=0;
        String sql="SELECT quantity FROM material WHERE materialnumber=? AND quality=?";
        String update="UPDATE material SET quantity=? WHERE materialnumber=? AND quality=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            PreparedStatement pstmupdate=con.prepareStatement(update);
            pstm.setString(1,material);
            pstm.setString(2, quality);
            ResultSet result=pstm.executeQuery();
            if(result.next()){
                total=result.getDouble("quantity");
            
            }
            total-=qty;
            
            pstmupdate.setDouble(1, total);
            pstmupdate.setString(2, material);
            pstmupdate.setString(3, quality);
            
            pstmupdate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void getSelectedID(String id){
        this.selectedid=id;
    }
    
    public String setSelectedMatID(){
        return selectedid;
    }
    
    
}
