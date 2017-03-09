package sample.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teddy on 2017-03-08.
 */
public class RedoMenu {
    private List<EditCommand> redoStack;


    public RedoMenu() {
        redoStack = new ArrayList<>();
    }

    public EditCommand pop() {
        if(redoStack.isEmpty()) {
            return null;
        }
        return redoStack.remove(redoStack.size()-1);
    }
    public void push(EditCommand command){
        redoStack.add(command);
    }
    public boolean isEmpty() {
        return redoStack.isEmpty();
    }
}
