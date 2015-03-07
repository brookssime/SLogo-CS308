package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class Sum extends CommandNode {

    public Sum(Model myModel) {
        super(null, Double.class, Double.class);
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        return putObjectInList((double) args.get(0) + (double) args.get(1));
    }

}
