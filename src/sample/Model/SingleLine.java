package sample.Model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Created by Teddy on 2017-03-08.
 */
public class SingleLine extends LinePrototype{
    Line line;

    public SingleLine() {
        super();
        line = new Line();
    }

    /*
    Private constructor for cloning
     */
    private SingleLine(SingleLine singleLine) {
        //System.out.println("singleLine: X=" + singleLine.getX() + " Y="+ singleLine.getY() + " endX=" + singleLine.getEndX() + "  endY = " + singleLine.getEndY());
        line = new Line(singleLine.getStartX(), singleLine.getStartY(), singleLine.getEndX(), singleLine.getEndY());
        line.setStrokeWidth(singleLine.getStrokeWidth());
        //System.out.println("Line: Color: " + singleLine.color);
        line.setStroke(singleLine.getColor());
        this.color = singleLine.getColor();
        this.startX = singleLine.startX;
        this.startY = singleLine.startY;
        this.endX = singleLine.endX;
        this.endY = singleLine.endY;
        this.strokeWidth = singleLine.getStrokeWidth();
    }

    @Override
    public javafx.scene.shape.Shape draw() {
        Line line2 = new Line();
        line2.setStartX(super.startX);
        line2.setStartY(super.startY);
        line2.setEndX(super.endX);
        line2.setEndY(super.endY);
        line2.setStrokeWidth(super.getStrokeWidth());
        line2.setStroke(super.getColor());
        //System.out.println("line2: X=" + line2.getCenterX() + " Y="+ line2.getCenterY() + " endX=" + line2.getEndX() + "  endY = " + line2.getEndY());
        return line2;
    }

    @Override
    public Shape clone() {
        return new SingleLine(this);
    }

}
