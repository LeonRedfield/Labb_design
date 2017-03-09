

package sample.Model;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.View.Observer;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Teddy on 2017-03-08.
 */


public class DrawDocument extends Subject{
    //For testing
    private List<Shape> shapeList;


    public DrawDocument() {
        super();
        shapeList = new ArrayList<>();

    }

    public List<Shape> readDrawData()
    {
        return shapeList;
    }

    public void writeDrawData(Shape shape)
    {
        Shape tmp = shape.clone();
        System.out.println("Tmp in writeDrawData: X=" + tmp.getX() + " Y="+ tmp.getY() + " endX=" + tmp.getEndX() + "  endY = " + tmp.getEndY());
        shapeList.add(tmp);
        notifyAllObservers();
    }

    public void deleteDrawData(Shape shape) {

    }


}