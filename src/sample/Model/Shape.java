package sample.Model;

import javafx.scene.paint.Color;

import java.util.Objects;

/**
 * Created by Teddy on 2017-03-08.
 */
public abstract class Shape implements Cloneable{
    protected double strokeWidth;
    protected Color color;

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    abstract public javafx.scene.shape.Shape draw();
    @Override
    abstract public Shape clone();
}
