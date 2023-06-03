package view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Ball {

    private double angle;
    private final Circle circle;
    private final Line line;
    private final Label label;

    public Ball(double deg, String labelText) {
        this.angle = deg;
        this.circle = new Circle(GameView.MainCircleCenterX, GameView.MainCircleCenterY + GameView.ballsRotationRadius,
                GameView.getCurrentGameView().getBallsRadius(), Color.BLACK);
        this.line = new Line(GameView.MainCircleCenterX, GameView.MainCircleCenterY + GameView.MainCircleRadius,
                GameView.MainCircleCenterX, GameView.MainCircleCenterY + GameView.ballsRotationRadius);
        this.label = new Label(labelText);
        label.setStyle("-fx-font-size: " + GameView.ballsFontSize + "px;-fx-text-fill: white;");
        setBallsByAngle();
    }

    public double getAngle() {
        return angle;
    }

    public double getX() {
        return circle.getCenterX();
    }

    public double getY() {
        return circle.getCenterY();
    }

    public double getRadius() {
        return circle.getRadius();
    }

    public void changeRadius(double amount) {
        circle.setRadius(circle.getRadius() + amount);
    }

    public void moveToEndPos(double distance) {
        double x = (distance + GameView.ballsRotationRadius) * Math.cos(this.angle) + GameView.MainCircleCenterX;
        double y = (distance + GameView.ballsRotationRadius) * Math.sin(this.angle) + GameView.MainCircleCenterY;
        draw(x, y);
    }

    public void changeAngle(double angle) {
        this.angle += angle;
        setBallsByAngle();
    }

    private void setBallsByAngle() {
        double x = GameView.ballsRotationRadius * Math.cos(this.angle) + GameView.MainCircleCenterX;
        double y = GameView.ballsRotationRadius * Math.sin(this.angle) + GameView.MainCircleCenterY;
        draw(x, y);
    }

    private void draw(double x, double y) {
        double lineStartX = GameView.MainCircleRadius * Math.cos(this.angle) + GameView.MainCircleCenterX;
        double lineStartY = GameView.MainCircleRadius * Math.sin(this.angle) + GameView.MainCircleCenterY;
        this.circle.setCenterX(x);
        this.circle.setCenterY(y);
        this.line.setStartX(lineStartX);
        this.line.setStartY(lineStartY);
        this.line.setEndX(x);
        this.line.setEndY(y);
        label.setTranslateX(x - (label.getText().length() < 2 ? 4 : 8));
        label.setTranslateY(y - 10);
    }

    public void show(Pane pane) {
        pane.getChildren().add(circle);
        pane.getChildren().add(line);
        pane.getChildren().add(label);
    }

    public void setOpacity(double opacity) {
        this.circle.setOpacity(opacity);
        this.line.setOpacity(opacity);
        this.label.setOpacity(opacity);
    }

}
