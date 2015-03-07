package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class Minus extends CommandNode {

    public Minus(Model myModel) {
        super(null, Double.class);
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        return putObjectInList((double) args.get(0) * -1);
    }

}
