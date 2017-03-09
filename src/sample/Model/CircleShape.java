package sample.Model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * Created by Teddy on 2017-03-08.
 */
public class CircleShape extends Shape{
    private Circle circle;

    public CircleShape() {
        super();
        circle = new Circle();
    }

    private CircleShape(CircleShape circleShape)
    {
        circle = new Circle(circleShape.getX(), circleShape.getY(), circleShape.width);
        this.startX = circleShape.startX;
        this.startY = circleShape.startY;
        this.endX = circleShape.endX;
        this.endY = circleShape.endY;
    }

    @Override
    public javafx.scene.shape.Shape draw() {

        //System.out.println("line2: X=" + line2.getStartX() + " Y="+ line2.getStartY() + " endX=" + line2.getEndX() + "  endY = " + line2.getEndY());
        Circle c = new Circle(super.getX(), super.getY(), super.width);
        c.setStroke(Color.BLACK);
        return c;
    }

    @Override
    public Shape clone() {
        return new CircleShape(this);
    }

}
