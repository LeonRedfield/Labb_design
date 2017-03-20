package sample.Model;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * Created by Teddy on 2017-03-16.
 */
public abstract class RectanglePrototype extends Shape{
    protected double startX;
    protected double startY;
    protected double width;
    protected double height;
    protected boolean isFilled;
    public final Button rectangleButton = new Button();

    protected RectanglePrototype()
    {
        isFilled = false;
    }

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


    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
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

    public Button getRectangleButton() {
        return rectangleButton;
    }

}
