package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class Right extends CommandNode {

    public Right(Model myModel) {
        super(myModel, Double.class);
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        double degrees = ((double) args.get(0)) % Turtle.getFullRotation();
        myTurtle.setHeading(myTurtle.getHeading() + degrees);
        return putObjectInList(degrees);
    }

}
