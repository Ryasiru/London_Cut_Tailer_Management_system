
package londoncut.models.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import londoncut.db.DBConnection;
import londoncut.models.Expense;


public class ExpenseController {
    
    public void addExpense(Expense exp){
        String sql="INSERT INTO expense(date,description,expense) VALUES (?,?,?)";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, exp.getDate());
            pstm.setString(2, exp.getDescription());
            pstm.setDouble(3, exp.getExpense());
            
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExpenseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
