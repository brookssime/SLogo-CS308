package commands;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import exceptions.InvalidWindowBehaviorException;
import tree.CommandNode;
import application.Model;
import application.Turtle;


public class Forward extends CommandNode {
    private double maxX;
    private double maxY;
    private static final String METHOD_BASE = "advanceTurtle";
    private Method method;

    public Forward (Model myModel) {
        super(myModel, Double.class);
    }

    /**
     * Runs the appropriate advanceTurtle method (Wrap or Fence, currently) using reflection. Gets
     * the windowType from Model.
     */
    @Override
    public List<Object> function (Turtle myTurtle, List<Object> args) {
        double distance = (double) args.get(0);
        maxX = getModel().getMaxX();
        maxY = getModel().getMaxY();
        String methodName = METHOD_BASE + getModel().getWindowType();
        try {
            method = this.getClass().getDeclaredMethod(methodName, Turtle.class, double.class);
        }
        catch (SecurityException | NoSuchMethodException e) {
            throw new InvalidWindowBehaviorException(getModel().getWindowType());
        }
        try {
            method.invoke(this, myTurtle, distance);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new InvalidWindowBehaviorException(getModel().getWindowType());
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

    /**
     * advances the turtle but will not take it past the edge of the window
     * @param myTurtle
     * @param distance
     * @return the distance moved packaged in a List<Object>
     */
    private List<Object> advanceTurtleFence (Turtle myTurtle, double distance) {
        System.out.println("here");
        double radiansHeading = Math.toRadians(myTurtle.getHeading());
        double x = myTurtle.getX() + distance * Math.cos(radiansHeading);
        double y = myTurtle.getY() + distance * Math.sin(radiansHeading);
        if (outOfXBounds(x)) {
            y = maxX * y / x;
            x = maxX;
        }
        if (outOfYBounds(y)) {
            x = x * maxY / y;
            y = maxY;
        }
        return putObjectInList(myTurtle.setLocation(x, y));
    }

    private boolean outOfXBounds (double x) {
        return (Math.abs(x) > maxX);
    }

    private boolean outOfYBounds (double y) {
        return (Math.abs(y) > maxY);
    }

}
