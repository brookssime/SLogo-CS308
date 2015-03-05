package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class PenDown extends CommandNode {

    public PenDown(Model myModel) {
        super(myModel, 0);
    }

    @Override
    public List<Object> function(List<Object> args) {
        myModel.getActiveTurtle().setPenDown(true);
        return putObjectInList(1);
    }

}
