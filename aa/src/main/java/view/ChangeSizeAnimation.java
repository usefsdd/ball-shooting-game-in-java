package view;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class ChangeSizeAnimation extends Transition {

    Pane pane;

    public ChangeSizeAnimation(Pane pane) {
        this.pane = pane;
        this.setCycleCount(INDEFINITE);
        this.setCycleDuration(Duration.seconds(3));
    }

    @Override
    protected void interpolate(double v) {
        if (v <= 0.51) {
            GameView.getCurrentGameView().changeBallsRadius(0.03);
        } else {
            GameView.getCurrentGameView().changeBallsRadius(-0.03);
        }
        ArrayList<Ball> allBalls = GameView.getCurrentGameView().getBalls();
        for (int i = 0; i < allBalls.size(); i++) {
            for (int j = i+1; j < allBalls.size(); j++) {
                Ball thisBall = allBalls.get(i);
                Ball nextBall = allBalls.get(j);
                double distance = Math.sqrt(Math.pow(thisBall.getX() - nextBall.getX(), 2) +
                        Math.pow(thisBall.getY() - nextBall.getY(), 2));
                if (distance <= (thisBall.getRadius() + nextBall.getRadius() - 0.2)) {
                    this.stop();
                    GameView.getCurrentGameView().lose();
                }
            }
        }
    }
}
