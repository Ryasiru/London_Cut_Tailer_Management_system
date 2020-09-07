
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
import londoncut.tables.tblMeasurementData;


public class tblMeasurementController {
    
   public ObservableList<tblMeasurementData> getDatatoTabel(String data){
       String sql="SELECT suitnumber,type,material,measurement,qty,deliverydate FROM suit WHERE suitnumber=?;";
       ObservableList<tblMeasurementData>list=FXCollections.observableArrayList();
       Connection con=DBConnection.getInstance().getConnection();
       try {
           PreparedStatement pstm=con.prepareStatement(sql);
           pstm.setString(1,data);
           ResultSet result=pstm.executeQuery();
           while(result.next()){
               list.add(new tblMeasurementData(
                       result.getString("suitnumber"),
                       result.getString("type"),
                       result.getString("material"),
                       result.getString("measurement"),
                       result.getInt("qty"),
                       result.getString("deliverydate")
                       
                ));
           }
       } catch (SQLException ex) {
           Logger.getLogger(tblMeasurementController.class.getName()).log(Level.SEVERE, null, ex);
       }
       return list;
   }
}
