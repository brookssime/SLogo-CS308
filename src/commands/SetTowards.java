package commands;

import java.util.List;

import application.Model;
import application.Turtle;

public class SetTowards extends CommandNode {

    public SetTowards(Model myModel) {
        super(myModel, 2);
    }

    @Override
    public List<Object> function(List<Object> args) {
        Turtle turtle = myModel.getActiveTurtle();
        double x = (double) args.get(0);
        double y = (double) args.get(1);
        double dx = x - turtle.getX();
        double dy = y - turtle.getY();
        double newHeading = Math.toDegrees(Math.atan(dy / dx))
                + getOffset(dx, dy);
        double oldHeading = turtle.getHeading();
        turtle.setHeading(newHeading);
        return putObjectInList(Math.abs(newHeading - oldHeading));
    }

    private double getOffset(double x, double y) {
        if (x > 0 && y > 0) {
            return 0;
        } else if (x < 0) {
            return Turtle.getHalfRotation();
        } else {
            return Turtle.getFullRotation();
        }
    }

}
