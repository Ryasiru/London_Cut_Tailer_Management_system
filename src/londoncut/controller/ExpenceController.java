
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import londoncut.Main;
import londoncut.alerts.MessageAlert;
import londoncut.models.Expense;
import londoncut.models.controller.ExpenseController;
import londoncut.tables.controller.tblExpenseController;
import londoncut.tables.tblExpense;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class ExpenceController implements Initializable {

    @FXML
    private JFXButton btnadd;
    @FXML
    private JFXButton btnhome;
    @FXML
    private JFXButton btnsearch;
    @FXML
    private TextField txtexpence;
    @FXML
    private TextField txtdesc;
   
    @FXML
    private TableView<tblExpense> tblexpense;
    @FXML
    private TableColumn<tblExpense, String> colDate;
    @FXML
    private TableColumn<tblExpense, String> colDesc;
    @FXML
    private TableColumn<tblExpense, Double> colExpense;
    
    @FXML
    private JFXDatePicker expDate;
    @FXML
    private JFXDatePicker fromDate;
    @FXML
    private JFXDatePicker toDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTable();
        tblexpense.setItems(new tblExpenseController().loadData() );
    }    

    @FXML
    private void loadHome(ActionEvent event) throws IOException {
        Parent next=FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
        Scene scene=new Scene(next);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void addExpense(ActionEvent event) {
        if(expDate.getValue().format(DateTimeFormatter.ISO_DATE).isEmpty()||txtdesc.getText().isEmpty()||txtexpence.getText().isEmpty()){
            MessageAlert.ShowMessage("Missing Data", "Error", Alert.AlertType.ERROR);
        }else{
            new ExpenseController().addExpense(new Expense(expDate.getValue().format(DateTimeFormatter.ISO_DATE),
                    txtdesc.getText(), Double.parseDouble(txtexpence.getText())));
            tblexpense.setItems(new tblExpenseController().loadData());
        }
        
    }
    
    private void initiateTable(){
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colExpense.setCellValueFactory(new PropertyValueFactory<>("expense"));
    }

    @FXML
    private void searchExpense(ActionEvent event) {
        if(fromDate.getValue().format(DateTimeFormatter.ISO_DATE).isEmpty()||toDate.getValue().format(DateTimeFormatter.ISO_DATE).isEmpty()){
            MessageAlert.ShowMessage("Missing Date ", "Alert", Alert.AlertType.INFORMATION);
        }else{
            tblexpense.setItems(new tblExpenseController().searchExp(fromDate.getValue().format(DateTimeFormatter.ISO_DATE),
                    toDate.getValue().format(DateTimeFormatter.ISO_DATE)));
        }
        
    }

    @FXML
    private void setExpDate(ActionEvent event) {
        
        if(expDate.getValue()!=null){
            String local=expDate.getValue().format(DateTimeFormatter.ISO_DATE);
            expDate.getEditor().setText(local);
        }else{
            expDate.getEditor().setText("YY/MM/DD");
        }


    }

    @FXML
    private void setFromDAte(ActionEvent event) {
        if(fromDate.getValue()!=null){
            String local=fromDate.getValue().format(DateTimeFormatter.ISO_DATE);
            fromDate.getEditor().setText(local);
        }else{
            fromDate.getEditor().setText("YY/MM/DD");
        }

        
    }

    @FXML
    private void setToDate(ActionEvent event) {
        if(toDate.getValue()!=null){
            String local=toDate .getValue().format(DateTimeFormatter.ISO_DATE);
            toDate.getEditor().setText(local);
        }else{
            toDate.getEditor().setText("YY/MM/DD");
        }

    }
    
}
