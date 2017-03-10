package sample.Model;

/**
 * Created by Teddy on 2017-03-08.
 */
public class AddDrawObject implements EditCommand {
    private Shape drawObject;
    private DrawDocument drawDocument;

    public AddDrawObject(DrawDocument drawDocument, Shape drawObject)
    {
        this.drawDocument = drawDocument;
        this.drawObject = drawObject;
    }



    public Shape getDrawObject() {
        return (Shape) drawObject;
    }
    public Shape getDrawObjectClone() {
        return (Shape) drawObject.clone();
    }


    @Override
    public void undo() {

        drawDocument.deleteDrawData(drawObject);
        drawDocument.notifyAllObservers();
    }

    @Override
    public void redo() {
        drawDocument.writeDrawData(drawObject);
    }

    @Override
    public void execute() {
        drawDocument.writeDrawData(drawObject);
    }

    @Override
    public String toString() {
        return "add";
    }
}
