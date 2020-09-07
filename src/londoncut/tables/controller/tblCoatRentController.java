
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
import londoncut.tables.tblCoatRent;
import londoncut.tables.tblDeleteCoat;


public class tblCoatRentController {
    
    public ObservableList<tblCoatRent > loadData(){
        ObservableList<tblCoatRent>list=FXCollections.observableArrayList();
        String sql="SELECT coatnumber , coatcolour , rentprice FROM coat WHERE status='Available'";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(new tblCoatRent(result.getString("coatnumber"),
                        result.getString("coatcolour"),
                        result.getDouble("rentprice")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblCoatRentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public ObservableList<tblCoatRent> autoLoad(String data){
         ObservableList<tblCoatRent> list=FXCollections.observableArrayList();
        String sql="SELECT coatnumber , coatcolour , rentprice FROM coat WHERE coatnumber LIKE '%"+data+"%' OR coatcolour LIKE '%"+data+"%' OR"
                + " rentprice LIKE '%"+data+"%'";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(new tblCoatRent(result.getString("coatnumber"),
                        result.getString("coatcolour"),
                        result.getInt("rentprice")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblDeleteCoatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
