package sample.Model;

import java.util.Objects;

/**
 * Created by Teddy on 2017-03-08.
 */
public abstract class Shape implements Cloneable{
    private String id;
    protected String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

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
