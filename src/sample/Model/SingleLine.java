package sample.Model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Created by Teddy on 2017-03-08.
 */
public class SingleLine extends Shape{
    Line line;

    public SingleLine() {
        super();
        line = new Line();
    }

    /*
    Private constructor for cloning
     */
    private SingleLine(SingleLine singleLine) {
        System.out.println("singleLine: X=" + singleLine.getX() + " Y="+ singleLine.getY() + " endX=" + singleLine.getEndX() + "  endY = " + singleLine.getEndY());
        line = new Line(singleLine.getX(), singleLine.getY(), singleLine.getEndX(), singleLine.getEndY());
        line.setStrokeWidth(singleLine.getWidth());
        this.startX = singleLine.startX;
        this.startY = singleLine.startY;
        this.endX = singleLine.endX;
        this.endY = singleLine.endY;
    }

    @Override
    public javafx.scene.shape.Shape draw() {
        Line line2 = new Line();
        line2.setStartX(super.startX);
        line2.setStartY(super.startY);
        line2.setEndX(super.endX);
        line2.setEndY(super.endY);
        line2.setStrokeWidth(5);
        line2.setFill(Color.BLACK);
        System.out.println("line2: X=" + line2.getStartX() + " Y="+ line2.getStartY() + " endX=" + line2.getEndX() + "  endY = " + line2.getEndY());
        return line2;
    }

    @Override
    public Shape clone() {
        SingleLine tmp = new SingleLine(this);
        System.out.println("Tmp: X=" + tmp.getX() + " Y="+ tmp.getY() + " endX=" + tmp.getEndX() + "  endY = " + tmp.getEndY());

        return tmp;
    }

}
