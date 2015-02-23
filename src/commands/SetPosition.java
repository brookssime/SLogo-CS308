package commands;

import java.util.List;

import application.Model;
import application.Turtle;

public class SetPosition extends Command {

    public SetPosition(Model myModel) {
        super(myModel, 2);
    }
    
    // Need to add error checking for valid x and y values
    @Override
    public List<Object> function(List<Object> args) {
        Turtle turtle = myModel.getActiveTurtle();
        double x = (double) args.get(0);
        double y = (double) args.get(1);
        double oldX = turtle.getX();
        double oldY = turtle.getY();
        turtle.setLocation(x, y);
        return putDoubleInList(Math.sqrt(Math.pow((x - oldX), 2)
                + Math.pow((y - oldY), 2)));
    }

}
