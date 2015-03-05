package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class Left extends CommandNode {

    public Left(Model myModel) {
        super(myModel, 1);
    }

    @Override
    public List<Object> function(List<Object> args) {
        double degrees = (double) args.get(0) % Turtle.getFullRotation();
        Turtle turtle = myModel.getActiveTurtle();
        turtle.setHeading(turtle.getHeading() + (-1*degrees));
        return putObjectInList(degrees);
    }

}
