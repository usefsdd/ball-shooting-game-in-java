package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class LoginMenu extends Application {

    public static Stage stage = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Menu.getLoginController().runGame();
        if (LoginMenu.stage == null) {
            LoginMenu.stage = stage;
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initStyle(StageStyle.UNDECORATED);
        }
        URL url = new URL(LoginMenu.class.getResource("/FXML/LoginMenu.fxml").toExternalForm());
        Pane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        LoginMenu.stage.getIcons().add(new Image(LoginMenu.class.getResource("/img/aaGame.png").openStream()));
        stage.show();
    }

}
