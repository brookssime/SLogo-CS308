package commands;

import java.util.List;
import application.Model;
import application.Turtle;
import tree.CommandNode;

public class Fence extends CommandNode {

    public Fence (Model myModel) {
        super(myModel, new Class[0]);
    }

    @Override
    protected List<Object> function (Turtle myTurtle, List<Object> args) {
        getModel().setWindowType("Fence");
        return putObjectInList((double) 3);
    }

}
