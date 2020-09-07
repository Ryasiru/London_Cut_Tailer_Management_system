
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
import londoncut.models.controller.OrdersController;
import londoncut.tables.tblSuitDetail;


public class tblSuitDetailController {
    
    public ObservableList<tblSuitDetail>loadData(){
        ObservableList<tblSuitDetail>list=FXCollections.observableArrayList();
        String id=new OrdersController().setSelectOrder();
        String sql="SELECT s.suitnumber,s.type,s.deliverydate,s.qty,s.price FROM suit s,suitorder so WHERE "
                + "so.ordernumber=? AND so.suitnumber=s.suitnumber";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, id);
            ResultSet result= pstm.executeQuery();
            while(result.next()){
                list.add(new tblSuitDetail(result.getString("suitnumber"),
                        result.getString("type"), 
                        result.getString("deliverydate"), 
                        result.getInt("qty"), 
                        result.getDouble("price")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblSuitDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    
}
