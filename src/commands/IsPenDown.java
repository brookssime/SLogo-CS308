package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class IsPenDown extends CommandNode {

    public IsPenDown(Model myModel) {
        super(myModel, new Class[0]);
    }

    @Override
    public List<Object> function(List<Object> args) {
        if (myModel.getActiveTurtle().isPenDown()) {
            return putObjectInList((double) 1);
        }
        return putObjectInList((double) 0);
    }

}
