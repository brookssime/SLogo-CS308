package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class PenDown extends CommandNode {

    public PenDown(Model myModel) {
        super(myModel, new Class[0]);
    }

    @Override
    public List<Object> function(List<Object> args) {
        getModel().getActiveTurtle().setPenDown(true);
        return putObjectInList((double) 1);
    }

}
