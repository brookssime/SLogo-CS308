package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class Quotient extends CommandNode {

    public Quotient(Model myModel) {
        super(myModel, Double.class, Double.class);
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        return putObjectInList((double) args.get(0) / (double) args.get(1));
    }

}
