package view;

import javafx.animation.Transition;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class WinAnimation extends Transition {

    Pane pane;
    public WinAnimation(Pane pane) {
        this.pane = pane;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.seconds(1));
        pane.setBackground(new Background(new BackgroundFill(Color.rgb(5,178,86),null,null)));
    }

    @Override
    protected void interpolate(double v) {

        for (Ball ball : GameView.getCurrentGameView().getBalls()) {
            ball.moveToEndPos(1000 * v);
        }
    }
}
