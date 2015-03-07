package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class Sine extends CommandNode {

    public Sine(Model myModel) {
        super(myModel, Double.class);
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        return putObjectInList(Math.sin(Math.toRadians((double) args.get(0))));
    }

}
