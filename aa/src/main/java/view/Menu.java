package view;

import controller.GameController;
import controller.LoginController;
import controller.MainController;
import model.Game;

public class Menu {

    private static final LoginController loginController = new LoginController();
    private static final MainController mainController = new MainController();
    private static final GameController gameController = new GameController();

    public static LoginController getLoginController() {
        return loginController;
    }

    public static MainController getMainController() {
        return mainController;
    }

    public static GameController getGameController() {
        return gameController;
    }


}
