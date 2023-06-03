package view;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BallShoot extends Transition {

    private final Pane pane;
    private final LabeledCircle circle;
    private final double wind;
    private final boolean player2;

    public BallShoot(Pane pane, LabeledCircle circle, double wind, boolean player2) {
        this.pane = pane;
        this.circle = circle;
        this.wind = wind;
        this.player2 = player2;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        double circleX = circle.getCenterX();
        double circleY = circle.getCenterY();
        for (Ball ball : GameView.getCurrentGameView().getBalls()) {
            double ballsDistance = Math.sqrt(Math.pow(ball.getX() - circleX, 2) + Math.pow(ball.getY() - circleY, 2));
            if (ballsDistance <= (ball.getRadius() + circle.getRadius())) {
                circle.remove(pane);
                this.stop();
                GameView.getCurrentGameView().lose();
            }
        }
        double distanceToCenter =
                Math.sqrt((GameView.MainCircleCenterX - circleX) * (GameView.MainCircleCenterX - circleX)
                + (GameView.MainCircleCenterY - circleY) * (GameView.MainCircleCenterY - circleY));
        if (distanceToCenter <= 130) {
            this.stop();
            circle.remove(pane);
            Ball ball = new Ball(calculateAngle(circleX, circleY), circle.getLabelText());
            Menu.getGameController().addBall(ball);
            GameView.getCurrentGameView().addBall(ball);
            ball.show(pane);
            if (Menu.getGameController().getLeftBalls() == 0)
                GameView.getCurrentGameView().win();
        }
        if (player2) {
            circleY += 10;
        }else {
            circleY -= 10;
        }
        circleX += 10 * wind;
        circle.changePosition(circleX, circleY);
    }

    private double calculateAngle(double x, double y) {
        x -= 250;
        y -= 250;
        return Math.asin(y / Math.sqrt(x * x + y * y));
    }
}
