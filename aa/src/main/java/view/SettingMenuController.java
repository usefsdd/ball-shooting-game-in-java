package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;

public class SettingMenuController {

    public ComboBox difficultyCombo;
    public ComboBox ballsNumCombo;
    public ComboBox mapsCombo;
    public TextField shootControlKey;
    public TextField frozenControlKey;
    public Button muteBtn;
    public Button colorBtn;
    public Button langBtn;

    public boolean shootFocus;
    public boolean frozenFocus;

    @FXML
    public void initialize() {
        HashMap<String, String> setting = Menu.getLoginController().getSetting();
        difficultyCombo.getSelectionModel().select(Integer.parseInt(setting.get("difficulty")) - 1);
        for (int i = 8; i <= 24; i += 4) ballsNumCombo.getItems().add(i);
        ballsNumCombo.getSelectionModel().select((Integer.parseInt(setting.get("ballNum")) - 8) / 4);
        mapsCombo.getSelectionModel().select(Integer.parseInt(setting.get("map")) - 1);
        shootControlKey.setText(setting.get("shootKey"));
        frozenControlKey.setText(setting.get("freezeKey"));
        muteBtn.setText("Mute: " + setting.get("mute"));
        colorBtn.setText("Black&White: " + setting.get("backWhite"));
        langBtn.setText("Language: " + setting.get("language"));
        shootControlKey.requestFocus();
        shootControlKey.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                shootControlKey.setText(keyEvent.getCode().getName());
            }
        });
        frozenControlKey.requestFocus();
        frozenControlKey.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                frozenControlKey.setText(keyEvent.getCode().getName());
            }
        });
    }

    public void mute(MouseEvent mouseEvent) {
        if (muteBtn.getText().contains("ON"))
            muteBtn.setText(muteBtn.getText().replace("ON","OFF"));
        else muteBtn.setText(muteBtn.getText().replace("OFF","ON"));
    }

    public void switchColor(MouseEvent mouseEvent) {
        if (colorBtn.getText().contains("ON"))
            colorBtn.setText(colorBtn.getText().replace("ON","OFF"));
        else colorBtn.setText(colorBtn.getText().replace("OFF","ON"));
    }

    public void switchLang(MouseEvent mouseEvent) {
        if (langBtn.getText().contains("eng"))
            langBtn.setText(langBtn.getText().replace("eng","per"));
        else langBtn.setText(langBtn.getText().replace("per","eng"));
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        int difficulty = 1;
        switch ((String) difficultyCombo.getValue()) {
            case "Medium" -> difficulty = 2;
            case "Hard" -> difficulty = 3;
        }
        int map = 0;
        switch ((String) mapsCombo.getValue()) {
            case "Map1" -> map = 1;
            case "Map2" -> map = 2;
            case "Map3" -> map = 3;
        }
        Menu.getLoginController().updateSetting(difficulty, (int) ballsNumCombo.getValue(), map,
                shootControlKey.getText(), frozenControlKey.getText(), muteBtn.getText().contains("ON"),
                colorBtn.getText().contains("ON"), langBtn.getText().contains("eng") ? "eng" : "per");
        Menu.getGameController().savePlayers();
        new MainMenu().start(LoginMenu.stage);
    }
}
