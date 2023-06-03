package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class PauseMenu extends Application {

    public static Pane pane;

    @Override
    public void start(Stage stage) throws Exception {
        URL url = new URL(LoginMenu.class.getResource("/FXML/PauseMenu.fxml").toExternalForm());
        pane = FXMLLoader.load(url);
        pane.requestFocus();
        PauseMenu.pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();
                if (keyName.equals("Esc")) {
                    PauseMenuController.toGame();
                }
            }
        });
        Scene scene = new Scene(pane);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
}
