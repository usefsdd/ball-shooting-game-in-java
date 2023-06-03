package view;

import javafx.scene.input.MouseEvent;

public class MainMenuController {
    public void newGame(MouseEvent mouseEvent) throws Exception {
        Menu.getMainController().startGame();
        new GameMenu().start(LoginMenu.stage);
    }

    public void multiPlayer(MouseEvent mouseEvent) throws Exception {
        Menu.getMainController().startGame();
        GameView.getCurrentGameView().setMultiPlayer(true);
        new GameMenu().start(LoginMenu.stage);
    }

    public void enterProfile(MouseEvent mouseEvent) throws Exception {
        new ProfileMenu().start(LoginMenu.stage);
    }

    public void showScoreBoard(MouseEvent mouseEvent) throws Exception {
        new ScoreBoardMenu().start(LoginMenu.stage);
    }

    public void enterSetting(MouseEvent mouseEvent) throws Exception {
        new SettingMenu().start(LoginMenu.stage);
    }

    public void LogOut(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(LoginMenu.stage);
    }

}
