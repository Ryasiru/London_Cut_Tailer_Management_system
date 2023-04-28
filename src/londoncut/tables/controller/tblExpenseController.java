
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
import londoncut.tables.tblExpense;


public class tblExpenseController {
    
    public ObservableList<tblExpense > loadData(){
        ObservableList<tblExpense>list=FXCollections.observableArrayList();
        String sql="SELECT date,description,expense FROM expense ORDER BY expenseid DESC ";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result= pstm.executeQuery();
            while(result.next()){
                list.add(new tblExpense(result.getString("date"),
                        result.getString("description"), 
                        result.getDouble("expense")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblExpenseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public ObservableList<tblExpense> searchExp(String from,String to){
        ObservableList<tblExpense>list=FXCollections.observableArrayList();
        String sql="SELECT date,description,expense FROM expense WHERE expdate BETWEEN ? AND ?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, from);
            pstm.setString(2, to);
            
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(new tblExpense(result.getString("date"),
                        result.getString("description"), 
                        result.getDouble("expense")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblExpenseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
