package view;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import view.messages.RegisterMessages;

public class RegisterMenuController {
    public TextField usernameTxt;
    public PasswordField passwordTxt;
    public PasswordField passwordConfirmTxt;
    public TextField emailTxt;

    public void register(MouseEvent mouseEvent) throws Exception {
        RegisterMessages messages = Menu.getLoginController().register(usernameTxt.getText(), passwordTxt.getText(),
                passwordConfirmTxt.getText(), emailTxt.getText());
        String errorStyle = "-fx-border-color: #cc0000;-fx-effect: dropshadow(three-pass-box, #cc0000, 20, 0, 0, 0);" +
                "-fx-prompt-text-fill: #cc0000;-fx-font-size: 10px";
        switch (messages) {
            case USERNAME_IN_USE -> {
                usernameTxt.setStyle(errorStyle);
                usernameTxt.setText("");
                usernameTxt.setPromptText("invalid username");
            }
            case WEEK_PASSWORD_LENGTH -> {
                passwordTxt.setStyle(errorStyle);
                passwordConfirmTxt.setStyle(errorStyle);
                passwordTxt.setText("");
                passwordConfirmTxt.setText("");
                passwordTxt.setPromptText("at least 8 characters");
            }
            case WEEK_PASSWORD_UPPER_CASE -> {
                passwordTxt.setStyle(errorStyle);
                passwordConfirmTxt.setStyle(errorStyle);
                passwordTxt.setText("");
                passwordConfirmTxt.setText("");
                passwordTxt.setPromptText("at least 1 upper case letter");
            }
            case WEEK_PASSWORD_LOWER_CASE -> {
                passwordTxt.setStyle(errorStyle);
                passwordConfirmTxt.setStyle(errorStyle);
                passwordTxt.setText("");
                passwordConfirmTxt.setText("");
                passwordTxt.setPromptText("at least 1 lower case letter");
            }
            case WEEK_PASSWORD_NUMBER -> {
                passwordTxt.setStyle(errorStyle);
                passwordConfirmTxt.setStyle(errorStyle);
                passwordTxt.setText("");
                passwordConfirmTxt.setText("");
                passwordTxt.setPromptText("at least 1 numeric letter");
            }
            case WEEK_PASSWORD_CHARACTER -> {
                passwordTxt.setStyle(errorStyle);
                passwordConfirmTxt.setStyle(errorStyle);
                passwordTxt.setText("");
                passwordConfirmTxt.setText("");
                passwordTxt.setPromptText("at least 1 character of list: !@#$%^&*()-_+=?><.,'[]{}|\\");
            }
            case WRONG_PASSWORD_REPEAT -> {
                passwordConfirmTxt.setStyle(errorStyle);
                passwordConfirmTxt.setText("");
                passwordConfirmTxt.setPromptText("wrong password confirm");
            }
            case INVALID_EMAIL_FORMAT -> {
                emailTxt.setStyle(errorStyle);
                emailTxt.setText("");
                emailTxt.setPromptText("incorrect email");
            }
            case REGISTER_SUCCESS -> {
                new LoginMenu().start(LoginMenu.stage);
            }
        }
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(LoginMenu.stage);
    }

    public void resetStyle(KeyEvent keyEvent) {
        String style = "-fx-background-color: rgba(215, 215, 215, 0.84);-fx-prompt-text-fill: #6b6b6b;" +
                "-fx-text-fill: black;-fx-effect: none;-fx-border-style: none;-fx-font-size: 15px;";
        ((TextField) keyEvent.getSource()).setStyle(style);
        usernameTxt.setPromptText("username");
        passwordTxt.setPromptText("password");
        passwordConfirmTxt.setPromptText("password confirm");
        emailTxt.setPromptText("email");
    }
}
