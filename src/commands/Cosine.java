package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class Cosine extends CommandNode {

    public Cosine(Model myModel) {
        super(myModel, Double.class);
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        return putObjectInList(Math.cos(Math.toRadians((double) args.get(0))));
    }

}
