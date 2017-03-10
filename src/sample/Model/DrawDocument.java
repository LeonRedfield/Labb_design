

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

    public EditCommand writeDrawData(Shape shape)
    {
        Shape tmp = shape.clone();
        System.out.println("WriteData: " + tmp.getColor());
        //System.out.println("Tmp in writeDrawData: X=" + tmp.getX() + " Y="+ tmp.getY() + " endX=" + tmp.getEndX() + "  endY = " + tmp.getEndY() + "W ="+ tmp.getWidth() );
        shapeList.add(tmp);
        EditCommand command = new AddDrawObject(this, tmp);
        return command;
    }

    public void deleteDrawData(Shape shape)
    {
        System.out.println("Deleting...");
        int index = getShapeIndx(shape);
        if(index >= 0)
        {
            System.out.println("Found shape...Removing");
            shapeList.remove(index);
            notifyAllObservers();
        }

    }

    public void editDrawData(Shape shape, Shape shapeOverride)
    {
        int index = getShapeIndx(shape);
        if(index >= 0)
        {
            shapeList.remove(index);
            shapeList.add(shapeOverride);
            notifyAllObservers();
        }
    }

    private int getShapeIndx(Shape shape)
    {
        int i= 0;
        for(Shape s: this.shapeList)
        {
            System.out.println(s.toString() + " \n" +shape.toString());
            if(s.toString().equals(shape.toString()))
            {
                return i;
            }
            i++;
        }
        return -1;
    }



}