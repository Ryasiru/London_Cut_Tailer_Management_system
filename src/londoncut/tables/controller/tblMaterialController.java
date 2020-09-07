
package londoncut.tables.controller;

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
import londoncut.tables.tblMaterial;


public class tblMaterialController {
    
    public ObservableList<tblMaterial >loadData(){
        ObservableList<tblMaterial>list=FXCollections.observableArrayList();
        String sql="SELECT * FROM material ORDER BY quantity ASC";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result= pstm.executeQuery();
            while(result.next()){
                list.add(new tblMaterial(result.getString("materialnumber"),
                        result.getString("material"), 
                        result.getString("quality"),
                        result.getDouble("quantity")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(tblMaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ObservableList<tblMaterial> autoLoad(String data){
        ObservableList<tblMaterial> list=FXCollections.observableArrayList();
        String sql="SELECT * FROM material WHERE materialnumber LIKE '%"+data+"%' OR material LIKE '%"+data+"%' OR quality LIKE '%"
                +data+"%' OR quantity LIKE '%"+data+"%'";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
           
            while(result.next()){
                list.add(new tblMaterial(
                        result.getString("materialnumber"),
                        result.getString("material"),
                        result.getString("quality"),
                        result.getDouble("quantity")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Measurement_3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
}
