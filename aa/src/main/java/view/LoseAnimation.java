package view;

import javafx.animation.Transition;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class LoseAnimation extends Transition {

    Pane pane;

    public LoseAnimation(Pane pane) {
        this.pane = pane;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.seconds(1));
        pane.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
    }

    @Override
    protected void interpolate(double v) {
        for (Ball ball : GameView.getCurrentGameView().getBalls()){
            ball.changeAngle(0.05 * Math.PI);
            ball.moveToEndPos(1000 * v);
        }
    }
}
