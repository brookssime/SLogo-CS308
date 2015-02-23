package commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import application.Model;
import application.Turtle;

public class Forward extends Command {    
    private double maxX;
    private double maxY;

    public Forward(Model myModel) {
        super(myModel, 1);
    }
    @Override
    public List<Object> function(List<Object> args) {
        maxX = myModel.getMaxX();
        maxY = myModel.getMaxY();
        Turtle turtle = myModel.getActiveTurtle();
        double distance = (double) args.get(0);
        double radiansHeading = turtle.getRadiansHeading();
        double x = turtle.getX() + distance*Math.cos(radiansHeading);
        double y = turtle.getY() + distance*Math.sin(radiansHeading);
        List<Object> list = new ArrayList<>();
        if (outOfXBounds(x)) {
            boolean temp = turtle.isPenDown();
            if (x > 0) {
                x -= maxX;
                list.add(maxX/Math.cos(radiansHeading));
                function(list);
                turtle.setPenDown(false);
                turtle.setX(-maxX);
                turtle.setPenDown(temp);
                list.clear();
                list.add(x/Math.cos(radiansHeading));
                function(list);
            } else {
                x += maxX;
                list.clear();
                list.add(-maxX/Math.cos(radiansHeading));
                function(list);
                turtle.setPenDown(false);
                turtle.setX(maxX);
                turtle.setPenDown(temp);
                list.clear();
                list.add(x/Math.cos(radiansHeading));
                function(list);
            }
        } else if (outOfYBounds(y)) {
            boolean temp = turtle.isPenDown();
            if (y > 0) {
                y -= maxY;
                list.clear();
                list.add(maxY/Math.sin(radiansHeading));
                function(list);
                turtle.setPenDown(false);
                turtle.setX(-maxY);
                turtle.setY(Math.tan(radiansHeading));
                turtle.setPenDown(temp);
                list.clear();
                list.add(y/Math.sin(radiansHeading));             
                function(list);          
            } else {
                y += maxY;
                list.clear();
                list.add(-maxY/Math.sin(radiansHeading));
                function(list);
                turtle.setPenDown(false);
                turtle.setY(maxY);
                turtle.setPenDown(temp);
                list.clear();
                list.add(y/Math.sin(radiansHeading));
                function(list);
            }
        }
        turtle.setLocation(x, y);
        return putDoubleInList(distance);
    }
    
    private boolean outOfXBounds(double x) {
        return (Math.abs(x) > maxX);
    }
    
    private boolean outOfYBounds(double y) {
        return (Math.abs(y) > maxY);
    }

}
