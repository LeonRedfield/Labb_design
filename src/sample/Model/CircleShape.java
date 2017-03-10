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
        //System.out.println("circle: X= " + circleShape.getX()+ " Y="+circleShape.getY() + " w = " + circleShape.getWidth());
        circle = new Circle(circleShape.getX(), circleShape.getY(),circleShape.getRadius());
        circle.setStroke(circleShape.getColor());
        circle.setFill(Color.TRANSPARENT);
        circle.setStrokeWidth(circleShape.getThickness());
        if(circleShape.isFilled())
        {
            circle.setFill(circleShape.getColor());
        }

        this.thickness = circleShape.getThickness();
        this.color = circleShape.getColor();
        this.startX = circleShape.startX;
        this.startY = circleShape.startY;
        this.endX = circleShape.endX;
        this.endY = circleShape.endY;
        this.radius = circleShape.getRadius();
        this.isFilled = circleShape.isFilled;
    }

    @Override
    public javafx.scene.shape.Shape draw() {

        //System.out.println("line2: X=" + line2.getStartX() + " Y="+ line2.getStartY() + " endX=" + line2.getEndX() + "  endY = " + line2.getEndY());
        Circle c = new Circle(super.getX(), super.getY(), super.radius);
        c.setStroke(super.getColor());
        c.setStrokeWidth(super.getThickness());
        c.setFill(Color.TRANSPARENT);
        if(super.isFilled())
        {
            c.setFill(super.getColor());
        }

        //System.out.println("C : X= " + c.getCenterX()+ " Y="+c.getCenterY() + " w = " + c.getRadius());

        return c;
    }

    @Override
    public Shape clone() {
        return new CircleShape(this);
    }

}
