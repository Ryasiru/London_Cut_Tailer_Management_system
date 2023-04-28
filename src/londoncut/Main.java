/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package londoncut;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import londoncut.db.DBConnection;

/**
 *
 * @author Yasiru
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/FXMLDocument.fxml"));

        Image image = new Image("/londoncut/images/logoFinal.jpg");
        stage.getIcons().add(image);

        Scene scene = new Scene(root);

        stage.setTitle("The London Cut ");
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.initStyle(StageStyle.DECORATED.UNDECORATED);
        stage.show();

        DBConnection.getInstance();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
