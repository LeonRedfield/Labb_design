

package sample.Model;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Teddy on 2017-03-08.
 */
public class DrawDocument extends Subject{

    private Canvas canvas;
    private GraphicsContext gc;

    //For testing
    private List<Shape> shapeList;


    public DrawDocument(double height, double width) {
        super();
        canvas = new Canvas(width, height);
        shapeList = new ArrayList<>();

    }

    public Group readDrawData()
    {
        Group group = new Group();
        for(Shape s: shapeList) {
            group.getChildren().add(null);
        }
        return group;
    }

    public synchronized void writeDrawData(Shape shape)
    {
        shapeList.add(shape);
        notifyAllObservers();
    }


}