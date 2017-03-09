package sample.Model;

import java.util.Objects;

/**
 * Created by Teddy on 2017-03-08.
 */
public abstract class Shape implements Cloneable{
    private String id;
    protected Shape shape;
    protected double startX;
    protected double startY;
    protected double endX;
    protected double endY;

    protected double width;
    protected double height;
    protected String type;

    public Shape(){}

    public Shape(Shape shape, double startX, double startY, double endX, double endY, double width, double height, String type) {
        this.shape = shape;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.width = width;
        this.height = height;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return startX;
    }

    public void setX(double x) {
        this.startX = x;
    }

    public double getY() {
        return startY;
    }

    public void setY(double y) {
        this.startY = y;
    }

    public void setEnd(double x, double y){
        System.out.println("set endX=" + x + "  setendY="+ y);
        endX = x;
        endY = y;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
    abstract public javafx.scene.shape.Shape draw();
    @Override
    abstract public Shape clone();
}
