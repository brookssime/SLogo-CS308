package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class Heading extends CommandNode {

    public Heading(Model myModel) {
        super(myModel, 0);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList(myModel.getActiveTurtle().getHeading());
    }

}
