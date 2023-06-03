package view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PauseMenuController {
    public Button musicPauseButton;
    public Button changeMusicButton;
    public Label shoot;
    public Label freeze;
    public Label shoot2;
    public Label move;
    public Button save;
    public Button quit;
    public static Stage newStage;

    @FXML
    public void initialize() {
        if (GameView.getCurrentGameView().isPause()) musicPauseButton.setText("Music: PAUSE");
        else musicPauseButton.setText("Music: PLAY");
        changeMusicButton.setText("Track: " + GameView.getCurrentGameView().getTrack());
    }

    public void musicPause(MouseEvent mouseEvent) {
        if (musicPauseButton.getText().contains("PLAY")) {
            GameView.getCurrentGameView().getMediaPlayer().pause();
            musicPauseButton.setText("Music: PAUSE");
        } else {
            GameView.getCurrentGameView().getMediaPlayer().play();
            musicPauseButton.setText("Music: PLAY");
        }

    }

    public void musicTrack(MouseEvent mouseEvent) {
        GameView gameView = GameView.getCurrentGameView();
        gameView.changeTrack();
        gameView.getMediaPlayer().stop();
        gameView.setMusicPlayer(gameView.getTrack()-1);
        gameView.getMediaPlayer().play();
        if (musicPauseButton.getText().contains("PAUSE"))
            gameView.getMediaPlayer().pause();
        changeMusicButton.setText("Track: " + gameView.getTrack());
    }

    public void BackToGame(MouseEvent mouseEvent) throws Exception {
       toGame();
    }

    public void QuitGame(MouseEvent mouseEvent) throws Exception {
        GameView gameView = GameView.getCurrentGameView();
        gameView.getBallRotation().stop();
        gameView.getReverseAnimation().stop();
        gameView.getChangeSizeAnimation().stop();
        gameView.getFadeInOutAnimation().stop();
        gameView.getMediaPlayer().stop();
        new MainMenu().start(LoginMenu.stage);
    }

    public static void toGame() {
        GameView gameView = GameView.getCurrentGameView();
        gameView.getBallRotation().play();
        if (Menu.getGameController().currentPhase() >= 2) {
            gameView.getReverseAnimation().play();
            gameView.getChangeSizeAnimation().play();
            if (Menu.getGameController().currentPhase() >= 3)
                gameView.getFadeInOutAnimation().play();
        }
        newStage.close();
    }
}
