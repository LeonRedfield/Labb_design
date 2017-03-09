package sample.Model;

import javafx.scene.layout.Pane;
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
    javafx.scene.shape.Shape draw() {
        line.setStartX(super.x);
        line.setStartY(super.y);
        line.setEndX(this.endX);
        line.setEndY(this.endY);
        line.setStrokeWidth(super.width);
        return line;
    }

    public void setStart(double x, double y)
    {
        super.x = x;
        super.y = y;
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
