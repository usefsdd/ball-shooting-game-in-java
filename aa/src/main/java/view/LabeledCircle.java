package view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class LabeledCircle extends Circle {

    private final Label label;
    private final int fontSize;

    public LabeledCircle(double x, double y, double radius, Paint paint, String labelNum, int fontSize) {
        super(x, y, radius, paint);
        this.label = new Label(labelNum);
        this.fontSize = fontSize;
        label.setStyle("-fx-font-size: " + fontSize + "px;-fx-text-fill: white;");
        label.setTranslateX(x - fontSize * 0.25);
        label.setTranslateY(y - fontSize * 0.75);
        if (label.getText().length() > 1) label.setTranslateX(x - fontSize * 0.4);
    }

    public String getLabelText() {
        return label.getText();
    }

    public void setLabelText(String text) {
        this.label.setText(text);
        double x = super.getCenterX();
        if (label.getText().length() > 1) label.setTranslateX(x - fontSize * 0.4);
        else label.setTranslateX(x - fontSize * 0.25);
    }

    public void changePosition(double x, double y) {
        super.setCenterX(x);
        super.setCenterY(y);
        label.setTranslateX(x - (int) (fontSize * 0.25));
        label.setTranslateY(y - (int) (fontSize * 0.75));
        if (label.getText().length() > 1) label.setTranslateX(x - (int) (fontSize * 0.4));
    }

    public void changeLabel(String labelText) {
        this.label.setText(labelText);
    }

    public void changeSize(double radius) {
        super.setRadius(radius);
    }

    public void show(Pane pane) {
        pane.getChildren().add(this);
        pane.getChildren().add(label);
    }

    public void remove(Pane pane) {
        pane.getChildren().remove(this);
        pane.getChildren().remove(label);
    }
}
