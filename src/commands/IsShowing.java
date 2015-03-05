package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class IsShowing extends CommandNode {

    public IsShowing(Model myModel) {
        super(myModel, 0);
    }

    @Override
    public List<Object> function(List<Object> args) {
        if (myModel.getActiveTurtle().isShowing()) {
            return putObjectInList(1);
        }
        return putObjectInList(0);
    }

}
