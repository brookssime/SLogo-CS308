package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class PenUp extends CommandNode {

    public PenUp(Model myModel) {
        super(myModel, new Class[0]);
    }

    @Override
    public List<Object> function(List<Object> args) {
        myModel.getActiveTurtle().setPenDown(false);
        return putObjectInList((double) 0);
    }

}
