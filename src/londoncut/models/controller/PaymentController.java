
package londoncut.models.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import londoncut.db.DBConnection;
import londoncut.models.Payment;

public class PaymentController {
    
    public void savePayment(Payment payment ){
        String sql="INSERT INTO payment(ordernumber,paymentdate,payment,total) VALUES(?,?,?,?)";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,payment.getOrdernumber());
            pstm.setString(2,payment.getPaymentdate());
            pstm.setDouble(3,payment.getPayment());
            pstm.setDouble(4,payment.getTotal());
            
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void updatePayment(String oldPayment,String Newpayment,String ordernumber){
        Double totalpayment=Double.parseDouble(oldPayment)+Double.parseDouble(Newpayment);
        String sql="UPDATE payment SET payment=? WHERE ordernumber=? ";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setDouble(1,totalpayment);
            pstm.setString(2,ordernumber);
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
