package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class Pi extends CommandNode {

    public Pi(Model myModel) {
        super(null, new Class[0]);
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        return putObjectInList(Math.PI);
    }

}
