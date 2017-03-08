package sample.Model;

import javafx.scene.layout.Pane;

/**
 * Created by Teddy on 2017-03-08.
 */
public class Rectangle extends Shape {
    Rectangle rectangle;

    public Rectangle()
    {
        this.type = "Rectangle";
        rectangle = new Rectangle();
    }

    @Override
    void draw(Pane rootPane) {
    }
}
