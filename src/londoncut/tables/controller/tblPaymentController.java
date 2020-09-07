
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
import londoncut.tables.tblPayment;


public class tblPaymentController {

    public ObservableList<tblPayment > loadTable(String ordernumber){
        ObservableList<tblPayment> list=FXCollections.observableArrayList();
        String sql="SELECT type , qty FROM suit s , suitorder so WHERE so.ordernumber=? AND so.suitnumber=s.suitnumber";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,ordernumber);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(new tblPayment(result.getString("type"),result.getInt("qty")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return list;
         
    }
}
