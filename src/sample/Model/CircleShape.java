package sample.Model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Created by Teddy on 2017-03-08.
 */
public class CircleShape extends Shape{
    private Circle circle;
    private double radius;

    public CircleShape() {
        super();
        circle = new Circle();
    }

    @Override
    javafx.scene.shape.Shape draw() {
        return null;
    }

    public Circle getCircle()
    {
        return circle;
    }


}
