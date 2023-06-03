package controller;

import model.Game;
import model.Player;
import view.GameMenu;
import view.GameView;
import view.LoginMenu;
import view.messages.LoginMessages;
import view.messages.RegisterMessages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController {

    public RegisterMessages register(String username, String password, String passwordConfirm, String email) {
        RegisterMessages message = checkUser(username, password, passwordConfirm, email, false);
        if (message.equals(RegisterMessages.REGISTER_SUCCESS)) {
            new Player(username, password, email);
            Player.savePlayers();
        }
        return message;
    }

    public LoginMessages login(String username, String password) {
        Player player = Player.getPlayerByUserName(username);
        if (player == null) return LoginMessages.INVALID_USERNAME;
        if (!player.checkPassword(password)) return LoginMessages.INCORRECT_PASSWORD;
        Player.setCurrentPlayer(player);
        return LoginMessages.LOGIN_SUCCESS;
    }

    public void startGameGuest() throws Exception {
        Player.setCurrentPlayer(null);
        new Game(20, 2);
        new GameView(2);
        new GameMenu().start(LoginMenu.stage);
    }

    public void runGame() {
        Player.recoveryPlayers();
    }

    public HashMap<String, String> getProfileInfo() {
        Player player = Player.getCurrentPlayer();
        HashMap<String, String> info = new HashMap();
        info.put("username", player.getUsername());
        info.put("password", player.getPassword());
        info.put("email", player.getEmail());
        info.put("avatar", player.getAvatar() + "");
        return info;
    }

    public RegisterMessages updateProfile(String username, String password, String passwordConfirm, String email) {
        Player player = Player.getCurrentPlayer();
        RegisterMessages message = checkUser(username, password, passwordConfirm, email, true);
        if (message.equals(RegisterMessages.REGISTER_SUCCESS)) {
            player.setUsername(username);
            player.setPassword(password);
            player.setEmail(email);
            Player.savePlayers();
        }
        return message;
    }

    public RegisterMessages checkUser(String username, String password, String passwordConfirm, String email, boolean update) {
        if ((!update || !Player.getCurrentPlayer().getUsername().equals(username)) && Player.getPlayerByUserName(username) != null)
            return RegisterMessages.USERNAME_IN_USE;
        if (password.length() < 8) return RegisterMessages.WEEK_PASSWORD_LENGTH;
        Matcher matcher = Pattern.compile("[A-Z]+").matcher(password);
        if (!matcher.find()) return RegisterMessages.WEEK_PASSWORD_UPPER_CASE;
        matcher = Pattern.compile("[a-z]+").matcher(password);
        if (!matcher.find()) return RegisterMessages.WEEK_PASSWORD_LOWER_CASE;
        matcher = Pattern.compile("[0-9]+").matcher(password);
        if (!matcher.find()) return RegisterMessages.WEEK_PASSWORD_NUMBER;
        matcher = Pattern.compile("[\\\\!@#$%^&*()_?/>|<,\\-+=\\[\\]{}]+").matcher(password);
        if (!matcher.find()) return RegisterMessages.WEEK_PASSWORD_CHARACTER;
        if (!password.equals(passwordConfirm)) return RegisterMessages.WRONG_PASSWORD_REPEAT;
        if (!Pattern.compile("[A-Za-z0-9_.]+@[A-Za-z]+.[A-Za-z]+").matcher(email).matches())
            return RegisterMessages.INVALID_EMAIL_FORMAT;
        return RegisterMessages.REGISTER_SUCCESS;
    }


    public void deleteAccount() {
        Player.getCurrentPlayer().removePlayer();
        Player.savePlayers();
    }

    public HashMap<String, String> getSetting() {
        Player player = Player.getCurrentPlayer();
        HashMap<String, String> setting = new HashMap<>();
        setting.put("difficulty", player.getDifficulty() + "");
        setting.put("ballNum", player.getStartBallsNum() + "");
        setting.put("map", player.getMap() + "");
        setting.put("shootKey", player.getControlKeys()[0]);
        setting.put("freezeKey", player.getControlKeys()[1]);
        setting.put("mute", player.isMute() ? "ON" : "OFF");
        setting.put("backWhite", player.isBlackWhite() ? "ON" : "OFF");
        setting.put("language", player.getLanguage());
        return setting;
    }

    public void updateSetting(int difficulty, int ballNumber, int map, String shootKey, String freezeKey, boolean mute,
                              boolean blackWhite, String language) {
        Player player = Player.getCurrentPlayer();
        player.setDifficulty(difficulty);
        player.setStartBallsNum(ballNumber);
        player.setMap(map);
        player.setControlKeys(shootKey, freezeKey);
        player.setMute(mute);
        player.setColor(blackWhite);
        player.setLanguage(language);
    }

    public ArrayList<Player> getAllPlayers() {
        return Player.getAllPlayers();
    }

    public boolean isGuest() {
        return Player.getCurrentPlayer() == null;
    }
}
