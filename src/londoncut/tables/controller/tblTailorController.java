
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
import londoncut.tables.tblTailor;


public class tblTailorController {
    
    public ObservableList<tblTailor > loadTailorData(){
        ObservableList<tblTailor> list=FXCollections.observableArrayList();
        String sql="SELECT tailorid , fname , lname FROM tailor ";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                String id=result.getString("tailorid");
                String name=result.getString("fname")+" "+result.getString("lname");
                list.add(new tblTailor(id,name));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblTailorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
