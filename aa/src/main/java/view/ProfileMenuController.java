package view;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.Player;
import view.messages.RegisterMessages;

import java.util.HashMap;

public class ProfileMenuController {

    public TextField usernameTxt;
    public PasswordField passwordTxt;
    public PasswordField passwordConfirmTxt;
    public TextField emailTxt;
    public VBox avatar1;
    public VBox avatar2;
    public VBox avatar3;
    public VBox avatar4;
    public VBox avatar5;
    public VBox avatar6;

    @FXML
    public void initialize() {
        HashMap<String,String> info = Menu.getLoginController().getProfileInfo();
        usernameTxt.setText(info.get("username"));
        passwordTxt.setText(info.get("password"));
        passwordConfirmTxt.setText(info.get("password"));
        emailTxt.setText(info.get("email"));
        switch (Integer.parseInt(info.get("avatar"))) {
            case 1 -> selectedAvatar(avatar1);
            case 2 -> selectedAvatar(avatar2);
            case 3 -> selectedAvatar(avatar3);
            case 4 -> selectedAvatar(avatar4);
            case 5 -> selectedAvatar(avatar5);
            case 6 -> selectedAvatar(avatar6);
        }
    }

    public void submit(MouseEvent mouseEvent) throws Exception {
        RegisterMessages messages = Menu.getLoginController().updateProfile(usernameTxt.getText(), passwordTxt.getText(),
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
            case REGISTER_SUCCESS -> new MainMenu().start(LoginMenu.stage);
        }
    }

    public void deleteAccount(MouseEvent mouseEvent) throws Exception {
        Menu.getLoginController().deleteAccount();
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

    public void setAvatar1(MouseEvent mouseEvent) {
        Player.getCurrentPlayer().setAvatar(1);
        resetSelectedAvatars();
        selectedAvatar(avatar1);
    }
    public void setAvatar2(MouseEvent mouseEvent) {
        Player.getCurrentPlayer().setAvatar(2);
        resetSelectedAvatars();
        selectedAvatar(avatar2);
    }
    public void setAvatar3(MouseEvent mouseEvent) {
        Player.getCurrentPlayer().setAvatar(3);
        resetSelectedAvatars();
        selectedAvatar(avatar3);
    }
    public void setAvatar4(MouseEvent mouseEvent) {
        Player.getCurrentPlayer().setAvatar(4);
        resetSelectedAvatars();
        selectedAvatar(avatar4);
    }
    public void setAvatar5(MouseEvent mouseEvent) {
        Player.getCurrentPlayer().setAvatar(5);
        resetSelectedAvatars();
        selectedAvatar(avatar5);
    }
    public void setAvatar6(MouseEvent mouseEvent) {
        Player.getCurrentPlayer().setAvatar(6);
        resetSelectedAvatars();
        selectedAvatar(avatar6);
    }

    public void selectedAvatar(VBox avatar) {
        avatar.setStyle("-fx-border-color: #4158d0;");
    }

    public void resetSelectedAvatars() {
        avatar1.setStyle("-fx-border-color: #c850c0;");
        avatar2.setStyle("-fx-border-color: #c850c0;");
        avatar3.setStyle("-fx-border-color: #c850c0;");
        avatar4.setStyle("-fx-border-color: #c850c0;");
        avatar5.setStyle("-fx-border-color: #c850c0;");
        avatar6.setStyle("-fx-border-color: #c850c0;");
    }

}
