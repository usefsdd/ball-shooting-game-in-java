package view;

import javafx.animation.*;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.paint.CycleMethod;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.Game;

import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BallRotation extends Transition {


    public BallRotation() {
        this.setCycleCount(INDEFINITE);
        this.setCycleDuration(Duration.millis(1000));
    }

    @Override
    protected void interpolate(double v) {
        for (Ball ball : GameView.getCurrentGameView().getBalls()) {
            Menu.getGameController().changeBallPos(ball.getAngle(), ball.getAngle() +
                    Menu.getGameController().getSpeed() * Math.PI);
            ball.changeAngle(Menu.getGameController().getSpeed() * Math.PI);
        }
        Instant now = Instant.now();
        java.time.Duration timeElapsed = java.time.Duration.between(GameView.getCurrentGameView().getStart(), now);
        if (timeElapsed.getSeconds() == 355) GameView.getCurrentGameView().lose();
        GameView.getCurrentGameView().getTimerLabel().setText("Time: " + timeElapsed.getSeconds() / 60 + ":" + timeElapsed.getSeconds() % 60);
    }
}
