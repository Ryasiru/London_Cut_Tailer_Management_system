
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
import londoncut.tables.tblReturnCoat;


public class tblReturnCoatController {
    
    public ObservableList<tblReturnCoat > loadData(){
        ObservableList<tblReturnCoat>list=FXCollections.observableArrayList();
        String sql="SELECT nic,customername,coatnumber,rentdate,returndate FROM rentcoat";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(new tblReturnCoat(result.getString("nic"),
                        result.getString("customername"),
                        result.getString("coatnumber"),
                        result.getString("rentdate"),
                        result.getString("returndate")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblReturnCoatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ObservableList<tblReturnCoat> autoLoad(String data){
        ObservableList<tblReturnCoat>list=FXCollections.observableArrayList();
        String sql="SELECT nic,customername,coatnumber,rentdate,returndate FROM rentcoat WHERE nic LIKE '%"+data+"%' OR "
                + "customername LIKE '%"+data+"%' OR coatnumber LIKE '%"+data+"%' OR rentdate LIKE '%"+data+"%' OR "
                + "returndate LIKE '%"+data+"%'";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(new tblReturnCoat(result.getString("nic"),
                        result.getString("customername"),
                        result.getString("coatnumber"),
                        result.getString("rentdate"),
                        result.getString("returndate")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblReturnCoatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
