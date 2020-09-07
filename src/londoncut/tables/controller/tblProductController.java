
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
import londoncut.tables.tblProduct;


public class tblProductController {
    
    public ObservableList<tblProduct > loadData(String ordernumber){
        ObservableList<tblProduct>list=FXCollections.observableArrayList();
        String sql="SELECt productname,qty,price FROM sellproduct WHERE ordernumber=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, ordernumber);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(new tblProduct(result.getString("productname"), 
                        result.getInt("qty"), 
                        result.getInt("price")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
