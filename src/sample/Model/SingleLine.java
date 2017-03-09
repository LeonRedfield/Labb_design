package sample.Model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Created by Teddy on 2017-03-08.
 */
public class SingleLine extends Shape{
    Line line;
    double endX;
    double endY;

    public SingleLine() {
        super();
        line = new Line();
    }

    @Override
    public javafx.scene.shape.Shape draw() {
        Line line2 = new Line();
        line2.setStartX(super.startX);
        line2.setStartY(super.startY);
        line2.setEndX(this.endX);
        line2.setEndY(this.endY);
        line2.setStrokeWidth(5);
        line2.setFill(Color.BLACK);
        return line2;
    }

    public void setStart(double x, double y)
    {
        super.startX = x;
        super.startY = y;
    }

    public void setEnd(double x, double y)
    {
        this.endX = x;
        this.endY = y;
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

}
