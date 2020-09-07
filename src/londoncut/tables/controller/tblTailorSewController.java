
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
import londoncut.tables.tblTailorSew;


public class tblTailorSewController {
       
    public ObservableList<tblTailorSew > loadOrder(){
        ObservableList<tblTailorSew >list=FXCollections.observableArrayList();
        String sql="SELECT * FROM tailorsewwork WHERE qty>0";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(new tblTailorSew(result.getString("ordernumber"),
                        result.getString("suitnumber"),
                        result.getString("type"),
                        result.getString("measurement"),
                        result.getString("deliverydate"), 
                        result.getInt("qty")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblTailorSewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    } 
}
