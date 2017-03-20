package sample.Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by Teddy on 2017-03-08.
 */
public class CircleShape extends CirclePrototype{
    private Circle circle;

    public CircleShape() {
        super();
        circle = new Circle();
    }

    private CircleShape(CircleShape circleShape)
    {
        super();
        //System.out.println("circle: X= " + circleShape.getX()+ " Y="+circleShape.getY() + " w = " + circleShape.getStrokeWidth());
        circle = new Circle(circleShape.getCenterX(), circleShape.getCenterY(),circleShape.getRadius());
        circle.setStroke(circleShape.getColor());
        circle.setFill(Color.TRANSPARENT);
        circle.setStrokeWidth(circleShape.getStrokeWidth());
        if(circleShape.isFilled())
        {
            circle.setFill(circleShape.getColor());
        }

        this.strokeWidth = circleShape.getStrokeWidth();
        this.color = circleShape.getColor();
        this.centerX = circleShape.centerX;
        this.centerY = circleShape.centerY;
        this.radius = circleShape.getRadius();
        this.isFilled = circleShape.isFilled;
    }

    @Override
    public javafx.scene.shape.Shape draw() {

        //System.out.println("line2: X=" + line2.getCenterX() + " Y="+ line2.getCenterY() + " endX=" + line2.getEndX() + "  endY = " + line2.getEndY());
        Circle c = new Circle(super.getCenterX(), super.getCenterY(), super.radius);
        c.setStroke(super.getColor());
        c.setStrokeWidth(super.getStrokeWidth());
        c.setFill(Color.TRANSPARENT);
        if(super.isFilled())
        {
            c.setFill(super.getColor());
        }
        return c;
    }

    @Override
    public Shape clone() {
        return new CircleShape(this);
    }

}
