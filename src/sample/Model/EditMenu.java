package sample.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teddy on 2017-03-16.
 */
public class EditMenu {
    private List<EditCommand> editStack;

    public EditMenu() {
        editStack = new ArrayList<>();
    }

    public EditCommand pop() {
        if(editStack.isEmpty()) {
            return null;
        }
        return editStack.remove(editStack.size()-1);
    }

    public void push(EditCommand command) {
        editStack.add(command);
    }

    public boolean isEmpty(){
        return editStack.isEmpty();
    }
}
