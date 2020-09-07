
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
import londoncut.tables.tblDeleteCoat;
import londoncut.tables.tblUpdateCoat;


public class tblUpdateCoatController {
    
    public ObservableList<tblUpdateCoat  > loadData(){
        ObservableList<tblUpdateCoat> list=FXCollections.observableArrayList();
        String sql="SELECT * FROM coat";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(new tblUpdateCoat(result.getString("coatnumber"),
                        result.getString("coatcolour"),
                        result.getInt("rentprice"),
                        result.getString("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblDeleteCoatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ObservableList<tblUpdateCoat> autoLoad(String data){
        ObservableList<tblUpdateCoat> list=FXCollections.observableArrayList();
        String sql="SELECT * FROM coat WHERE coatnumber LIKE '%"+data+"%' OR coatcolour LIKE '%"+data+"%' OR"
                + " rentprice LIKE '%"+data+"%' OR status LIKE '%"+data+"%' ";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(new tblUpdateCoat(result.getString("coatnumber"),
                        result.getString("coatcolour"),
                        result.getInt("rentprice"),
                        result.getString("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblDeleteCoatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
}
