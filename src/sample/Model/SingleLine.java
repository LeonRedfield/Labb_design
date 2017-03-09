package sample.Model;

import javafx.scene.layout.Pane;
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

    public Line getLine() {
        return line;
    }

    @Override
    void draw() {

    }
}
