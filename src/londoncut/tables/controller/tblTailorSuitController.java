
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
import londoncut.tables.tblTailorSuit;


public class tblTailorSuitController {
    
    public ObservableList<tblTailorSuit>loadData(String data){
        ObservableList<tblTailorSuit>list=FXCollections.observableArrayList();
        String sql="SELECT t.fname , t.lname FROM tailor t,tailorwork tw WHERE tw.suitnumber=? AND tw.tailorid=t.tailorid";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,data);
            ResultSet result= pstm.executeQuery();
            while(result.next()){
                list.add(new tblTailorSuit(result.getString("fname"),
                        result.getString("lname")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblTailorSuitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
