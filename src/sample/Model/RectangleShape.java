package sample.Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Teddy on 2017-03-09.
 */
public class RectangleShape extends RectanglePrototype {
    private Rectangle rectangle;

    public RectangleShape() {
        super();
        rectangle = new Rectangle();
    }

    private RectangleShape(RectangleShape rectangleShape)
    {
        rectangle = new Rectangle(rectangleShape.getStartX(), rectangleShape.getStartY(),rectangleShape.getWidth(), rectangleShape.getHeight());
        rectangle.setStroke(rectangleShape.getColor());
        rectangle.setStrokeWidth(rectangleShape.getStrokeWidth());
        rectangle.setFill(Color.TRANSPARENT);
        if(rectangleShape.isFilled())
        {
            rectangle.setFill(rectangleShape.getColor());
        }

        this.strokeWidth = rectangleShape.getStrokeWidth();
        this.color = rectangleShape.getColor();
        this.startX = rectangleShape.startX;
        this.startY = rectangleShape.startY;
        this.width = rectangleShape.width;
        this.height = rectangleShape.height;
        this.isFilled = rectangleShape.isFilled;
    }

    @Override
    public javafx.scene.shape.Shape draw() {

        Rectangle r = new Rectangle(super.startX, super.startY,super.width, super.getHeight());
        r.setStroke(super.getColor());
        r.setStrokeWidth(super.getStrokeWidth());
        r.setFill(Color.TRANSPARENT);
        if(super.isFilled())
        {
            r.setFill(super.getColor());
        }
        return r;
    }

    @Override
    public Shape clone() {
        return new RectangleShape(this);
    }
}
