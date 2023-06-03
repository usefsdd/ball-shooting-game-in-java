package view;

import javafx.animation.Transition;
import javafx.util.Duration;

public class FadeInOutAnimation extends Transition {

    public FadeInOutAnimation() {
        this.setCycleCount(INDEFINITE);
        this.setCycleDuration(Duration.seconds(3));
    }
    @Override
    protected void interpolate(double v) {
        for (Ball ball : GameView.getCurrentGameView().getBalls()) {
        if (v < 0.51) {
            ball.setOpacity(2 * v);
        } else {
            ball.setOpacity(2 * (1 - v));
        }

        }
    }
}
