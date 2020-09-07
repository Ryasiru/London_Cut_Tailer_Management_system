
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
import londoncut.tables.tblTailorPayment;


public class tblTailorPaymentController {

    public ObservableList<tblTailorPayment >loadData(){
        ObservableList<tblTailorPayment>list=FXCollections.observableArrayList();
        String sql="SELECT t.tailorid,fname,lname,sum(tw.qty) AS workdone ,salary  FROM tailorwork tw,tailor t WHERE "
                + " tw.tailorid=t.tailorid GROUP BY t.tailorid;  ";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(new tblTailorPayment(result.getString("tailorid"),
                        result.getString("fname"),
                        result.getString("lname"), 
                        result.getInt("workdone"),
                        result.getDouble("salary")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblTailorPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
