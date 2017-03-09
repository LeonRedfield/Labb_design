package sample.Model;

/**
 * Created by Teddy on 2017-03-08.
 */
public class AddDrawObject implements EditCommand{
    private Shape drawObject;
    private DrawDocument drawDocument;

    public AddDrawObject(DrawDocument drawDocument)
    {
        this.drawDocument =drawDocument;
    }



    public Shape getDrawObject() {
        return (Shape) drawObject;
    }
    public Shape getDrawObjectClone() {
        return (Shape) drawObject.clone();
    }


    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
