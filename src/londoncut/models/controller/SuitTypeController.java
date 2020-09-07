
package londoncut.models.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import londoncut.controller.Measurement_3Controller;
import londoncut.db.DBConnection;


public class SuitTypeController {
    
    public ObservableList<String> loadSuitType(){
        
        String sql="SELECT type FROM suittype;";
        Connection con=DBConnection.getInstance().getConnection();
        ObservableList<String> list=FXCollections.observableArrayList();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(result.getString("type"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SuitTypeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ObservableList<String> autoLoad(String data){
        ObservableList<String> list=FXCollections.observableArrayList();
        String sql="SELECT type FROM suittype WHERE type LIKE '%"+data+"%';";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(result.getString("type"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Measurement_3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }
            
}
