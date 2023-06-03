package view;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import view.messages.LoginMessages;

public class LoginMenuController {
    public TextField username;
    public TextField password;
    public void submit(MouseEvent mouseEvent) throws Exception {
        String user = username.getText();
        String pass = password.getText();
        LoginMessages loginMessage = Menu.getLoginController().login(user, pass);
        switch (loginMessage) {
            case INVALID_USERNAME -> {
                username.setStyle("-fx-border-color: #cc0000;" +
                        "-fx-effect: dropshadow(three-pass-box, #cc0000, 20, 0, 0, 0);" +
                        "-fx-prompt-text-fill: #cc0000");
                username.setText("");
                password.setText("");
                username.setPromptText("invalid username");
            }
            case INCORRECT_PASSWORD -> {
                password.setStyle("-fx-border-color: #cc0000;" +
                        "-fx-effect: dropshadow(three-pass-box, #cc0000, 20, 0, 0, 0);" +
                        "-fx-prompt-text-fill: #cc0000");
                password.setText("");
                password.setPromptText("wrong password");
            }
            case LOGIN_SUCCESS -> new MainMenu().start(LoginMenu.stage);
        }
    }

    public void cancel(MouseEvent mouseEvent) {
        username.setText("");
        password.setText("");
        System.exit(0);
    }

    public void guest(MouseEvent mouseEvent) throws Exception {
        Menu.getLoginController().startGameGuest();
        new GameMenu().start(LoginMenu.stage);
    }

    public void register(MouseEvent mouseEvent) throws Exception {
        new RegisterMenu().start(LoginMenu.stage);
    }

    public void resetStyle(KeyEvent keyEvent) {
        String style= "-fx-border-color: black;" +
                "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 1, 0);" +
                "-fx-prompt-text-fill: defualt";
        username.setStyle(style);
        username.setPromptText("username");
        password.setStyle(style);
        password.setPromptText("password");
    }

}
