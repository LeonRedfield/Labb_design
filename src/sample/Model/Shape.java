package sample.Model;

import java.util.Objects;

/**
 * Created by Teddy on 2017-03-08.
 */
public abstract class Shape implements Cloneable{
    private String id;
    protected Shape shape;
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected String type;

    public Shape(){}
    public Shape(double x, double y, double width, double height,String type) {
        this.x = x;
        this.y = y;
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
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
    abstract void draw();
    @Override
    public Object clone()
    {
        Object clone = null;
        try{
            clone = super.clone();
        }catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }

        return clone;
    }
}
