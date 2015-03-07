package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class SetHeading extends CommandNode {

    public SetHeading(Model myModel) {
        super(null, Double.class);
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        double newHeading = ((double) args.get(0)) % Turtle.getFullRotation();
        double oldHeading = myTurtle.getHeading();
        myTurtle.setHeading(newHeading);
        return putObjectInList(Math.abs(newHeading - oldHeading));
    }

}
