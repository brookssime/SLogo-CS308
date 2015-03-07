package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class IsShowing extends CommandNode {

    public IsShowing(Model myModel) {
        super(myModel, new Class[0]);
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        if (myTurtle.isShowing()) {
            return putObjectInList((double) 1);
        }
        return putObjectInList((double) 0);
    }

}
