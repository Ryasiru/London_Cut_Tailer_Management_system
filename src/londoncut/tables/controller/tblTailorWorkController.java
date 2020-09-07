
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
import londoncut.tables.tblTailorWork;


public class tblTailorWorkController {

    public ObservableList<tblTailorWork > loadData(){
        ObservableList<tblTailorWork>list=FXCollections.observableArrayList();
        String sql="SELECT tailorid ,fname,lname FROM tailor ";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result= pstm.executeQuery();
            while(result.next()){
                list.add(new tblTailorWork(result.getString("tailorid"),
                        result.getString("fname")+" "+result.getString("lname")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblTailorWorkController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public ObservableList<tblTailorWork > autoLoad(String data){
        ObservableList<tblTailorWork>list=FXCollections.observableArrayList();
        String sql="SELECT tailorid ,fname,lname FROM tailor WHERE tailorid LIKE '%"+data+"%' OR fname LIKE '%"+data+"%' OR "
                + "lname LIKE '%"+data+"%'" ;
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result= pstm.executeQuery();
            while(result.next()){
                list.add(new tblTailorWork(result.getString("tailorid"),
                        result.getString("fname")+" "+result.getString("lname")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblTailorWorkController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
