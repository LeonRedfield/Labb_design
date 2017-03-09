package sample.Model;

/**
 * Created by Teddy on 2017-03-08.
 */
public class DeleteDrawObject implements EditCommand{

    private DrawDocument drawDocument;
    private Shape drawObject;

    public DeleteDrawObject(DrawDocument drawDocument, Shape drawObject) {
        this.drawDocument = drawDocument;
        this.drawObject = drawObject;
    }

    @Override
    public void undo() {
        drawDocument.writeDrawData(drawObject);
    }

    @Override
    public void redo() {
        drawDocument.deleteDrawData(drawObject);
    }

    @Override
    public void execute() {
        drawDocument.deleteDrawData(drawObject);
    }
}
