
 
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import londoncut.Main;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class measurementController implements Initializable {

    @FXML
    private AnchorPane selectcustomerPane;
    @FXML
    private JFXButton btnback;
    @FXML
    private JFXButton btnnewcustomer;
    @FXML
    private JFXButton btnloyalcustomer;

    @FXML
    private void backHome(ActionEvent event) throws IOException {
        AnchorPane root=FXMLLoader.load(Main.class.getResource("views/homeOptions.fxml"));
        selectcustomerPane.getChildren().clear();
        selectcustomerPane.getChildren().add(root);
        FadeTransition t=new FadeTransition();
        t.setDuration(Duration.millis(1000));
        t.setNode(root);
        t.setFromValue(0);
        t.setToValue(1);
        t.play();

    }
    
    @FXML
    private void loadloyalcustomer(ActionEvent event) throws IOException {
        loadScene(event,"views/loyalcustomer.fxml");
    }
    
     @FXML
    private void loadaddcustomer(ActionEvent event) throws IOException {
         loadScene(event,"views/addnewcustomer.fxml");
    }

    public void loadScene(ActionEvent event,String name) throws IOException{
        Parent root=FXMLLoader.load(Main.class.getResource(name));
        Scene scene=new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        
        
        FadeTransition ft=new FadeTransition();
        ft.setDuration(Duration.millis(1000));
        ft.setNode(root);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        
        window.show();
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    

   
   
}
