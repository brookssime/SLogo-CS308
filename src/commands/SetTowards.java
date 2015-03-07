package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class SetTowards extends CommandNode {

    public SetTowards(Model myModel) {
        super(null, Double.class, Double.class);
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        double x = (double) args.get(0);
        double y = (double) args.get(1);
        double dx = x - myTurtle.getX();
        double dy = y - myTurtle.getY();
        double newHeading = Math.toDegrees(Math.atan(dy / dx))
                + getOffset(dx, dy);
        double oldHeading = myTurtle.getHeading();
        myTurtle.setHeading(newHeading);
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
