package commands;

import java.util.List;

import application.Model;
import application.Turtle;

public class Right extends Command {

    public Right(Model myModel) {
        super(myModel, 1);
    }

    @Override
    public List<Object> function(List<Object> args) {
        double degrees = ((double) args.get(0)) % Turtle.getFullRotation();
        Turtle turtle = myModel.getActiveTurtle();
        turtle.setHeading(turtle.getHeading() + degrees);
        return putObjectInList(degrees);
    }

}
