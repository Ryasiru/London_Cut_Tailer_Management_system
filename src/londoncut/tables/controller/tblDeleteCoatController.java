/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import londoncut.tables.tblDeleteCoat;

/**
 *
 * @author Yasiru
 */
public class tblDeleteCoatController {
    
    public ObservableList<tblDeleteCoat > loadData(){
        ObservableList<tblDeleteCoat> list=FXCollections.observableArrayList();
        String sql="SELECT * FROM coat";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(new tblDeleteCoat(result.getString("coatnumber"),
                        result.getString("coatcolour"),
                        result.getInt("price"),
                        result.getString("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblDeleteCoatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ObservableList<tblDeleteCoat> autoLoadData(String data){
        ObservableList<tblDeleteCoat> list=FXCollections.observableArrayList();
        String sql="SELECT * FROM coat WHERE coatnumber LIKE '%"+data+"%' OR coatcolour LIKE '%"+data+"%' OR"
                + " rentprice LIKE '%"+data+"%' OR status LIKE '%"+data+"%' ";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                list.add(new tblDeleteCoat(result.getString("coatnumber"),
                        result.getString("coatcolour"),
                        result.getInt("rentprice"),
                        result.getString("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblDeleteCoatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
}
