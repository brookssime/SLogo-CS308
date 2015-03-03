package commands;

import java.util.List;

import application.Model;
import application.Turtle;

public class SetHeading extends Command {

    public SetHeading(Model myModel) {
        super(myModel, 1);
    }

    @Override
    public List<Object> function(List<Object> args) {
        double newHeading = ((double) args.get(0)) % Turtle.getFullRotation();
        double oldHeading = myModel.getActiveTurtle().getHeading();
        myModel.getActiveTurtle().setHeading(newHeading);
        return putObjectInList(Math.abs(newHeading - oldHeading));
    }

}
