package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Player;

import java.net.URL;

public class GameMenu extends Application {

    LabeledCircle mainCircle;
    private Pane pane;

    @Override
    public void start(Stage stage) throws Exception {
        URL url = new URL(LoginMenu.class.getResource("/FXML/GameMenu.fxml").toExternalForm());
        pane = FXMLLoader.load(url);
        GameView.getCurrentGameView().setPane(pane);
        Scene scene = new Scene(pane);
        initInfoBar();
        createBalls();
        GameView.getCurrentGameView().getMediaPlayer().play();
        if (Player.getCurrentPlayer() != null)
            GameView.getCurrentGameView().getMediaPlayer().setMute(Menu.getGameController().mute());
        GameView.getCurrentGameView().getBallRotation().play();
        pane.requestFocus();
        keyHandler();
        stage.setScene(scene);
        stage.show();
    }

    private void initInfoBar() {
        Label remainBalls = new Label("Left Balls: " + Menu.getGameController().getLeftBalls());
        Label timer = new Label("Time: 00:00");
        Label score = new Label("Score: " + Menu.getGameController().getScore());
        pane.getChildren().addAll(remainBalls, timer, score);
        GameView.getCurrentGameView().setInfoBar(remainBalls, timer, score);
    }

    private void keyHandler() {
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();
                if (keyName.equals(Menu.getGameController().getControlKeys()[0])) {
                    try {
                        shootBall(false);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else if (keyName.equals(Menu.getGameController().getControlKeys()[1])) {
                    GameView.getCurrentGameView().freeze();
                } else if (keyName.equals("Enter")) {
                    if (GameView.getCurrentGameView().isMultiPlayer()) {
                        try {
                            shootBall(true);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else if (keyName.equals("Esc")) {
                    GameView gameView = GameView.getCurrentGameView();
                    gameView.getBallRotation().pause();
                    gameView.getReverseAnimation().pause();
                    gameView.getChangeSizeAnimation().pause();
                    gameView.getFadeInOutAnimation().pause();
                    try {
                        PauseMenuController.newStage = new Stage();
                        new PauseMenu().start(PauseMenuController.newStage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void createBalls() {
        mainCircle = new LabeledCircle(GameView.MainCircleCenterX, GameView.MainCircleCenterY,
                GameView.MainCircleRadius, Color.BLACK, "0", GameView.MainCircleFontSize);
        mainCircle.show(pane);
        createShooters(pane);
        for (Ball ball : GameView.getCurrentGameView().getBalls()) ball.show(pane);
    }

    private void createShooters(Pane pane) {
        LabeledCircle[] shooters = new LabeledCircle[4];
        for (int i = 0; i < 4; i++) {
            int y = GameView.shooterY + i * 50;
            shooters[i] = new LabeledCircle(GameView.MainCircleCenterX, y, GameView.shootBallsRadius, Color.BLACK,
                    (Menu.getGameController().getLeftBalls() - i) + "", GameView.ballsFontSize);
            shooters[i].show(pane);
        }
        GameView.getCurrentGameView().setShooters(shooters);
        if (GameView.getCurrentGameView().isMultiPlayer()) {
            LabeledCircle playerTwoShooter = new LabeledCircle(GameView.MainCircleCenterX,
                    20, GameView.shootBallsRadius, Color.BLACK, "", GameView.ballsFontSize);
            GameView.getCurrentGameView().setShooters(shooters);
            playerTwoShooter.show(pane);
            GameView.getCurrentGameView().setShooter2(playerTwoShooter);
        }
    }

    private void shootBall(boolean playerTwo) throws Exception {
        if (GameView.getCurrentGameView().isShootAvailable()) {
            Menu.getGameController().decreaseLeftBalls();
            new MediaPlayer(new Media((GameView.class.getResource("/soundEffects/shoot.mp3")).toExternalForm())).play();
            if (!playerTwo) {
                LabeledCircle circle = new LabeledCircle(GameView.MainCircleCenterX, GameView.shooterY, GameView.shootBallsRadius,
                        Color.BLACK, Menu.getGameController().getLeftBalls() + "", GameView.ballsFontSize);
                circle.show(pane);
                BallShoot ballShoot = new BallShoot(pane, circle, GameView.getCurrentGameView().getWind(), false);
                ballShoot.play();
            } else {
                LabeledCircle circle = new LabeledCircle(GameView.MainCircleCenterX, 20,
                        GameView.shootBallsRadius, Color.BLACK, Menu.getGameController().getLeftBalls() + "",
                        GameView.ballsFontSize);
                circle.show(pane);
                BallShoot ballShoot = new BallShoot(pane, circle, GameView.getCurrentGameView().getWind(), true);
                ballShoot.play();
            }
            int score = Menu.getGameController().getScore();
            GameView.getCurrentGameView().getScoreLabel().setText("Score: " + score);
            mainCircle.setLabelText(Menu.getGameController().getFrozenDegree() + "");
            int balls = Menu.getGameController().getLeftBalls();
            GameView.getCurrentGameView().getRemainBallsLabel().setText("Left Balls: " + balls);
            if (balls <= 5) GameView.getCurrentGameView().getRemainBallsLabel().setStyle("-fx-text-fill: green;" +
                    "-fx-font-size: 20px;");
            else if (balls <= score)
                GameView.getCurrentGameView().getRemainBallsLabel().setStyle("-fx-text-fill: yellow;" +
                        "-fx-font-size: 20px;");
            else GameView.getCurrentGameView().getRemainBallsLabel().setStyle("-fx-text-fill: red;" +
                        "-fx-font-size: 20px;");
            for (int i = 0; i < 4; i++) {
                LabeledCircle shooter = GameView.getCurrentGameView().getShooters()[i];
                int num = balls - i;
                shooter.setLabelText(num + "");
                LabeledCircle shooter2 = GameView.getCurrentGameView().getShooter2();
                if (i == 0 && shooter2 != null) shooter2.setLabelText(num + "");
                if (num < 1) shooter.remove(pane);
            }

            int phase = Menu.getGameController().getPhase();
            GameView gameView = GameView.getCurrentGameView();
            switch (phase) {
                case 2 -> {
                    gameView.getChangeSizeAnimation().play();
                    gameView.getReverseAnimation().play();
                }
                case 3 -> gameView.getFadeInOutAnimation().play();
                case 4 -> gameView.setMoveAvailable(true);
            }
        }
    }


}
