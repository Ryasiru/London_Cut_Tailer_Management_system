
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import londoncut.Main;
import londoncut.db.DBConnection;
import londoncut.tables.controller.tblTailorSuitController;
import londoncut.tables.controller.tblTailorWorkController;
import londoncut.tables.tblTailorSuit;
import londoncut.tables.tblTailorWork;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class TailorworkController implements Initializable {

    @FXML
    private JFXButton btnhome;
    @FXML
    private AnchorPane tailorworkPane;
    @FXML
    private JFXTextField txtsearch;
    @FXML
    private TableView<tblTailorWork> tbltailor;
    @FXML
    private TableColumn<tblTailorWork,String> colID;
    @FXML
    private TableColumn<tblTailorWork,String> colName;
    
    @FXML
    private JFXButton btnsearch;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtshirt;
    @FXML
    private TextField txtshort;
    
    @FXML
    private JFXDatePicker fromDate;
    @FXML
    private JFXDatePicker toDate;
    @FXML
    private TableView<tblTailorSuit> tbltailorsuit;
    @FXML
    private TableColumn<tblTailorSuit, String> colFname;
    @FXML
    private TableColumn<tblTailorSuit, String> colLname;
    @FXML
    private TextField txtsuitid;
    @FXML
    private JFXButton btnsearchtailor;

    @FXML
    private void loadhome(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
        Scene scene=new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition ft=new FadeTransition();
        ft.setDuration(Duration.millis(1000));
        ft.setNode(tailorworkPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        initiateTable();
        tbltailor.setItems(new tblTailorWorkController().loadData() );
    }    

    private void initiateTable(){
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colFname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colLname.setCellValueFactory(new PropertyValueFactory<>("lname"));
    }

    @FXML
    private void autoSearch(KeyEvent event) {
        tbltailor.setItems(new tblTailorWorkController().autoLoad(txtsearch.getText()));
    }

    @FXML
    private void loadFields(ActionEvent event) {
        
        setDefaultValue();
        
        String sql="SELECT tw.tailorid , s.type,sum(tw.qty) AS total FROM tailorwork tw ,suit s WHERE tw.tailorid='"+txtID.getText()+"' AND s.deliverydate BETWEEN '"+fromDate.getValue().format(DateTimeFormatter.ISO_DATE)+"' AND "
                + "'"+toDate.getValue().format(DateTimeFormatter.ISO_DATE)+"' AND "
                + " tw.suitnumber=s.suitnumber GROUP BY s.type";
        Connection con=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement pstm=con.prepareStatement(sql);
            
            
            ResultSet result=pstm.executeQuery();
            while(result.next()){
                String type=result.getString("type");
                if(type.equals("Shirt")){
                    txtshirt.setText(result.getString("total"));
                }else if(type.equals("Short")){
                    txtshort.setText(result.getString("total"));
                }else if(type.equals("")){
                    txtshort.setText(result.getString("total"));
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TailorworkController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void setSelectedID(MouseEvent event) {
        txtID.setText(tbltailor.getSelectionModel().getSelectedItem().getId());
    }
    
    
    private void setDefaultValue(){
        txtshirt.setText("0");
        txtshort.setText("0");
    }

    @FXML
    private void setToDate(ActionEvent event) {
        if(toDate.getValue()!=null){
            String local=toDate.getValue().format(DateTimeFormatter.ISO_DATE);
            toDate.getEditor().setText(local);
        }else{
            toDate.getEditor().setText("YY/MM/DD");
        }


    }

    @FXML
    private void setFromDate(ActionEvent event) {
        if(fromDate.getValue()!=null){
            String local=fromDate.getValue().format(DateTimeFormatter.ISO_DATE);
            fromDate.getEditor().setText(local);
        }else{
            fromDate.getEditor().setText("YY/MM/DD");
        }

    }

    @FXML
    private void getTailor(ActionEvent event) {
        tbltailorsuit.setItems(new tblTailorSuitController().loadData(txtsuitid.getText()));
    }
}
