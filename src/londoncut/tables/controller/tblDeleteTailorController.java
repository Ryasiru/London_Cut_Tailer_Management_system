
package londoncut.tables.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import londoncut.db.DBConnection;
import londoncut.models.Tailor;
import londoncut.tables.tblDeleteTailor;


public class tblDeleteTailorController {
    
    public ObservableList<tblDeleteTailor > loadData(){
        ObservableList<tblDeleteTailor>list=FXCollections.observableArrayList();
        String sql="SELECT tailorid,fname,lname,address,mobile,nic FROM tailor";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result= pstm.executeQuery();
            while(result.next()){
                list.add(new tblDeleteTailor(result.getString("tailorid"),
                        result.getString("fname"),
                        result.getString("lname"), 
                        result.getString("address"), 
                        result.getInt("mobile"), 
                        result.getString("nic")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblDeleteTailorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public ObservableList<tblDeleteTailor > autoLoad(String data){
        ObservableList<tblDeleteTailor>list=FXCollections.observableArrayList();
        String sql="SELECT tailorid,fname,lname,address,mobile,nic FROM tailor WHERE tailorid LIKE '%"+data+"%' OR "
                + "fname LIKE '%"+data+"%' OR lname LIKE '%"+data+"%' OR address LIKE '%"+data+"%' OR mobile LIKE '%"+data+"%' OR "
                + "nic LIKE '%"+data+"%'";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result= pstm.executeQuery();
            while(result.next()){
                list.add(new tblDeleteTailor(result.getString("tailorid"),
                        result.getString("fname"),
                        result.getString("lname"), 
                        result.getString("address"), 
                        result.getInt("mobile"), 
                        result.getString("nic")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblDeleteTailorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
