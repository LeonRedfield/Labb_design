package sample.Model;

import javafx.scene.paint.Color;

import java.util.Objects;

/**
 * Created by Teddy on 2017-03-08.
 */
public abstract class Shape implements Cloneable{
    private String id;
    protected javafx.scene.shape.Shape shape;
    protected double startX;
    protected double startY;
    protected double endX;
    protected double endY;
    protected double thickness;
    protected double width;
    protected double height;
    protected double radius;
    protected String type;
    protected Color color;
    protected boolean isFilled = false;
    //protected Color fillColor;

    public Shape(){}

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

    public javafx.scene.shape.Shape getShape() {
        return shape;
    }

    public void setShape(javafx.scene.shape.Shape shape) {
        this.shape = shape;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {

        isFilled = filled;
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

    public double getThickness() {
        return thickness;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
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
