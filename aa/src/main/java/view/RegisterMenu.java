package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class RegisterMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL url = new URL(LoginMenu.class.getResource("/FXML/RegisterMenu.fxml").toExternalForm());
        Pane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
