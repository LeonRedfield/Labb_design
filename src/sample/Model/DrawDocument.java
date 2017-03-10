

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
        System.out.println("WriteData: " + tmp.getColor());
        //System.out.println("Tmp in writeDrawData: X=" + tmp.getX() + " Y="+ tmp.getY() + " endX=" + tmp.getEndX() + "  endY = " + tmp.getEndY() + "W ="+ tmp.getWidth() );
        shapeList.add(tmp);
        notifyAllObservers();
    }

    public void deleteDrawData(Shape shape)
    {
        int index = getShapeIndx(shape);
        if(index >= 0)
        {
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
            System.out.println("S.draw =" + shape.toString() + "  shape.t=" + shapeOverride.toString()+ " index= " + index);
            shapeList.add(shapeOverride);
            notifyAllObservers();
        }
    }

    /*public void editDrawData(javafx.scene.shape.Shape shapeOverride)
    {

        int index = getShapeIndx(shapeOverride);

        if(index >= 0)
        {
            shapeList.get(index).setShape(shapeOverride);
            notifyAllObservers();
        }
    }*/

    private int getShapeIndx(Shape shape)
    {
        System.out.println("in getShapeIndx(Shape shape)");
        int i= 0;
        for(Shape s: this.shapeList)
        {
            System.out.println("getShapeIndx(Shape) : s.draw =" + s.toString() + "  shape.t=" + shape.toString());
            if(s.toString().equals(shape.toString()))
            {
                System.out.println("index found=" + i);
                return i;
            }
            i++;
        }
        return -1;
    }

    private int getShapeIndx(javafx.scene.shape.Shape shape)
    {
        System.out.println("in getShapeIndx(javafx.scene.shape.Shape shape)");
        int i= 0;
        for(Shape s: this.shapeList)
        {
            if(s.draw().toString().equals(shape.toString()))
            {
                System.out.println("getShape(scece.shape.Shape): s.draw =" + s.draw().toString() + "  shape.t=" + shape.toString() + " returning index=" + i);
                return i;
            }
            i++;
        }
        return -1;
    }

    public Shape getShapeOfFigureShape(javafx.scene.shape.Shape figureshape)
    {
        int index = getShapeIndx(figureshape);
        System.out.println("returnning Shape at index= "  + index);
        Shape shape =  shapeList.get(index);
        return shape;
    }



}