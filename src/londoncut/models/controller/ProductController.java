
package londoncut.models.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import londoncut.controller.SellproductController;
import londoncut.db.DBConnection;
import londoncut.models.Product;


public class ProductController {
    static String selectedid=null;
    
    public void saveProduct(Product product){
        String sql="INSERT INTO product VALUES (?,?,?,?,?)";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,product.getProductid());
            pstm.setString(2,product.getProductname());
            pstm.setInt(3,product.getUnitprice());
            pstm.setInt(4,product.getQty());
            pstm.setString(5,product.getDate());
            
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String setProductID(){
        String sql = "SELECT productid FROM product ORDER BY productid DESC LIMIT 1;";
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            String id = null;
            while (result.next()) {
                id = result.getString("productid");
            }
            if (id != null) {
                String[] arr = id.split("PI");
                int temp = Integer.parseInt(arr[1]);
                temp++;

                return temp < 10 ? "PI00" + temp : temp < 100 ? "PI0" + temp : "PI" + temp;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "PI001";
    }
    
    public void deleteProduct(String id){
         String sql="DELETE FROM product WHERE productid=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,id);
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void updateProduct(Product product){
        String sql="UPDATE product SET productname=? , unitprice=? , qty=? , purchaseddate=? WHERE productid=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, product.getProductname());
            pstm.setInt(2, product.getUnitprice());
            pstm.setInt(3, product.getQty());
            pstm.setString(4, product.getDate());
            pstm.setString(5, product.getProductid());
            
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<String > loadCombo(String data){
        ObservableList<String > list=FXCollections.observableArrayList();
        String sql="SELECT productname FROM product WHERE productname LIKE '%"+data+"%'";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(result.getString("productname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SellproductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public String getProductPrice(String id){
        String sql="SELECT unitprice FROM product WHERE productname=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1, id);
            ResultSet result= pstm.executeQuery();
            while(result.next()){
                return result.getString("unitprice");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return String.valueOf(0);
    }
    
    public ObservableList<String > loadData(){
        ObservableList<String > list=FXCollections.observableArrayList();
        String sql="SELECT productname FROM product ";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(result.getString("productname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SellproductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public void updateQty(String product,int qty){
        int total=0;
        String sql="SELECT qty FROM product WHERE productname=?";
        String update="UPDATE product SET qty=? WHERE productname=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            PreparedStatement pstmupdate=con.prepareStatement(update);
            pstm.setString(1,product);
            pstmupdate.setInt(1, total);
            pstmupdate.setString(2, product);
            
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                total=result.getInt("qty");
            }
            total=total-qty;
            pstmupdate.setInt(1, total);
            pstmupdate.setString(2, product);
            pstmupdate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setQty(String name,int qty){
        int total=0;
        String sql="SELECT qty FROM product WHERE productname=?";
        String update="UPDATE product SET qty=? WHERE productname=?";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            PreparedStatement pstmupdate=con.prepareStatement(update);
            pstm.setString(1,name);
            
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                total=result.getInt("qty");
            }
            total=total+qty;
            pstmupdate.setInt(1, total);
            pstmupdate.setString(2, name);
            pstmupdate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public String setSelectedId(){
        return selectedid;
    }
    
    public void getSelectedId(String id){
        this.selectedid=id;
    }
}
