/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import londoncut.Main;
import londoncut.alerts.MessageAlert;
import londoncut.db.DBConnection;
import londoncut.events.TextKeyHandling;
import londoncut.models.Customer;
import londoncut.models.controller.CustomerController;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class AddnewcustomerController implements Initializable {

    @FXML
    private JFXButton btnnext;
    @FXML
    private AnchorPane newcustomerPane;
    @FXML
    private JFXButton btnhome;
    @FXML
    private JFXTextField custid;
    @FXML
    private JFXTextField custfname;
    @FXML
    private JFXTextField custcontact;
    @FXML
    private Label nic;
    @FXML
    private JFXTextField custLname;
    @FXML
    private JFXTextField txtnic;

    @FXML
    private void backhome(ActionEvent event) throws IOException {
        Parent next = FXMLLoader.load(Main.class.getResource("views/FXMLDocument.fxml"));
        Scene scene = new Scene(next);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void getmeasurement(ActionEvent event) throws IOException, SQLException {

        if (txtnic.getText().isEmpty() || custfname.getText().isEmpty() || custLname.getText().isEmpty()
                || custcontact.getText().isEmpty()) {
            MessageAlert.ShowMessage("Missing Data", "Alert !", Alert.AlertType.WARNING);
        } else {

            new CustomerController().getSelectedCustomerid(custid.getText());
            boolean bool = new CustomerController().saveCustomer(new Customer(custid.getText(), custfname.getText(), custLname.getText(),
                    Integer.parseInt(custcontact.getText()), txtnic.getText()));

            Parent root = FXMLLoader.load(Main.class.getResource("views/measurement_3.fxml"));
            Scene scene = btnnext.getScene();
            root.translateXProperty().set(scene.getWidth());
            newcustomerPane.getChildren().add(root);

            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(Doevent -> {
                newcustomerPane.getChildren().remove(newcustomerPane);
            });
            timeline.play();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void setCustomerID(KeyEvent event) {
        custid.setText(new CustomerController().setCustomerID());
    }

   
    @FXML
    private void numbersOnly(KeyEvent event) {
         TextKeyHandling.onlyNumbers(event);
    }

    @FXML
    private void characterOnly(KeyEvent event) {
        TextKeyHandling.onlyCharacters(event);
    }


}
