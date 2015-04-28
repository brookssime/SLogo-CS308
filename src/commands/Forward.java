package commands;

import java.util.List;
import tree.CommandNode;
import application.Model;
import application.Turtle;


public class Forward extends CommandNode {
    private double maxX;
    private double maxY;

    public Forward (Model myModel) {
        super(myModel, Double.class);
    }

    @Override
    public List<Object> function (Turtle myTurtle, List<Object> args) {
        double distance = (double) args.get(0);
        maxX = getModel().getMaxX();
        maxY = getModel().getMaxY();
        if (getModel().getWindowType().equals("Wrap")) {
            return advanceTurtleWrap(myTurtle, distance);
        }
        else if (getModel().getWindowType().equals("Fence")) {
            return advanceTurtleFence(myTurtle, distance);
        }
        return putObjectInList(distance);
    }

    private List<Object> advanceTurtleWrap (Turtle myTurtle, double distance) {
        double radiansHeading = Math.toRadians(myTurtle.getHeading());
        double x = myTurtle.getX() + distance * Math.cos(radiansHeading);
        double y = myTurtle.getY() + distance * Math.sin(radiansHeading);
        boolean temp = myTurtle.isPenDown();
        if (outOfXBounds(x)) {
            double signX = Math.signum(x);
            double distX = signX * maxX - myTurtle.getX();
            double incrDist = distX / Math.cos(radiansHeading);
            double incrY = myTurtle.getY() + incrDist * Math.sin(radiansHeading);
            if (outOfYBounds(incrY)) {
                double signY = Math.signum(y);
                double distY = signY * maxY - myTurtle.getY();
                incrDist = distY / Math.sin(radiansHeading);
                double incrX = myTurtle.getX() + incrDist * Math.cos(radiansHeading);
                myTurtle.setLocation(incrX, signY * maxY);
                myTurtle.setPenDown(false);
                myTurtle.setLocation(incrX, -signY * maxY);
                myTurtle.setPenDown(temp);
                advanceTurtleWrap(myTurtle, distance - incrDist);
            }
            else {
                myTurtle.setLocation(signX * maxX, incrY);
                myTurtle.setPenDown(false);
                myTurtle.setLocation(-signX * maxX, incrY);
                myTurtle.setPenDown(temp);
                advanceTurtleWrap(myTurtle, distance - incrDist);
            }
        }
        else if (outOfYBounds(y)) {
            double signY = Math.signum(y);
            double distY = signY * maxY - myTurtle.getY();
            double incrDist = distY / Math.sin(radiansHeading);
            double incrX = myTurtle.getX() + incrDist * Math.cos(radiansHeading);
            myTurtle.setLocation(incrX, signY * maxY);
            myTurtle.setPenDown(false);
            myTurtle.setLocation(incrX, -signY * maxY);
            myTurtle.setPenDown(temp);
            advanceTurtleWrap(myTurtle, distance - incrDist);
        }
        else {
            myTurtle.setLocation(x, y);
        }
        return putObjectInList(distance);
    }

    private List<Object> advanceTurtleFence (Turtle myTurtle, double distance) {
        double radiansHeading = Math.toRadians(myTurtle.getHeading());
        double x = myTurtle.getX() + distance * Math.cos(radiansHeading);
        double y = myTurtle.getY() + distance * Math.sin(radiansHeading);
        if (outOfXBounds(x)) {
            y = maxX * y / x;
            x = maxX;
            if (outOfYBounds(y)) {
                x = x * maxY / y;
                y = maxY;
            }
        }
        return putObjectInList(Math.sqrt(Math.pow(myTurtle.getX() - x, 2) + Math.pow(myTurtle.getY() - y, 2)));
    }

    private boolean outOfXBounds (double x) {
        return (Math.abs(x) > maxX);
    }

    private boolean outOfYBounds (double y) {
        return (Math.abs(y) > maxY);
    }

}
