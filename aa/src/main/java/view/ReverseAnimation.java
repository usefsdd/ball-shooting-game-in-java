package view;

import javafx.animation.Transition;
import javafx.util.Duration;

public class ReverseAnimation extends Transition {

    public ReverseAnimation(double reverseTime) {
        this.setCycleCount(INDEFINITE);
        this.setCycleDuration(Duration.seconds(4 * reverseTime));
    }

    @Override
    protected void interpolate(double v) {
        if (v <= 0.50) {
            if (Menu.getGameController().getSpeed() < 0) Menu.getGameController().reverseRotation();
        } else {
            if (Menu.getGameController().getSpeed() > 0) Menu.getGameController().reverseRotation();
        }
    }
}
