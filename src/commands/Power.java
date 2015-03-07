package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class Power extends CommandNode {

    public Power(Model myModel) {
        super(myModel, Double.class, Double.class);
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        return putObjectInList(Math.pow((double) args.get(0), (double) args.get(1)));
    }

}
