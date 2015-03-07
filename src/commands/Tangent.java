package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class Tangent extends CommandNode {

    public Tangent(Model myModel) {
        super(myModel, Double.class);
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        return putObjectInList(Math.tan(Math.toRadians((double) args.get(0))));
    }

}
