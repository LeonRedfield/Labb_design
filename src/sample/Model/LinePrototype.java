package sample.Model;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Created by Teddy on 2017-03-16.
 */
public abstract class LinePrototype extends Shape {
    protected double startX;
    protected double startY;
    protected double endX;
    protected double endY;
    protected final Button lineButton = new Button();


    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public double getEndX() {
        return endX;
    }

    public void setEndX(double endX) {
        this.endX = endX;
    }

    public double getEndY() {
        return endY;
    }

    public void setEndY(double endY) {
        this.endY = endY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Button getLineButton() {
        return lineButton;
    }

}
