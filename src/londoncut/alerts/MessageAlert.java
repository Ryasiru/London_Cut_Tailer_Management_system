/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package londoncut.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Yasiru
 */
public class MessageAlert {

    public static void ShowMessage(String message, String headding, Alert.AlertType alertType) {

        Alert alert = new Alert(alertType);
        alert.setTitle(headding);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.show();

    }

    public static boolean confirm(String data) {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Delete "+data+" ? ", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(null);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            return true;
        }else{
            return false;
        }
    }
}
