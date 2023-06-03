package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class SettingMenu extends Application {
    private static Pane pane;
    @Override
    public void start(Stage stage) throws Exception {
        URL url = new URL(LoginMenu.class.getResource("/FXML/SettingMenu.fxml").toExternalForm());
        pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
