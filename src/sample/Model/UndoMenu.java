package sample.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teddy on 2017-03-08.
 */
public class UndoMenu {
    private List<EditCommand> undoStack;

    public UndoMenu() {
        undoStack = new ArrayList<>();
    }

    public EditCommand pop() {
        if(undoStack.isEmpty()) {
            return null;
        }
        return undoStack.remove(undoStack.size()-1);
    }

    public void push(EditCommand command) {
        undoStack.add(command);
    }

    public boolean isEmpty(){
        return undoStack.isEmpty();
    }
}
