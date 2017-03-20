package sample.Model;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;

/**
 * Created by Teddy on 2017-03-16.
 */
public abstract class CirclePrototype extends Shape {
    public final CheckBox fillCheckBox = new CheckBox("Fill");
    public final Button ovalButton = new Button();
    protected double centerX, centerY, radius;
    protected boolean isFilled;

    protected CirclePrototype()
    {
        isFilled = false;
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public CheckBox getFillCheckBox() {
        return fillCheckBox;
    }


    public Button getOvalButton() {
        return ovalButton;
    }

}
