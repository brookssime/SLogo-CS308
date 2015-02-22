package commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Forward extends Command {
    private double height;
    private double width;    
    
    public Forward() {
        numberOfParameters = 1;
    }
    @Override
    public double process(Turtle turtle, List<Object> parameters) {
        double distance = (double) parameters.next();
        double radiansHeading = turtle.getRadiansHeading();
        double x = turtle.getX() + distance*Math.cos(radiansHeading);
        double y = turtle.getY() + distance*Math.sin(radiansHeading);
        List<Object> list = new ArrayList<>();
        if (outOfXBounds(x)) {
            boolean temp = turtle.getPenDown();
            if (x > 0) {
                x -= width;
                list.add(width/Math.cos(radiansHeading));
                process(turtle, list.iterator());
                turtle.setPenDown(false);
                turtle.setX(-width);
                turtle.setPenDown(temp);
                list.clear();
                list.add(x/Math.cos(radiansHeading));
                process(turtle, list.iterator());
            } else {
                x += width;
                list.clear();
                list.add(-width/Math.cos(radiansHeading))
                process(turtle, list.iterator());
                turtle.setPenDown(false);
                turtle.setX(width);
                turtle.setPenDown(temp);
                list.clear();
                list.add(x/Math.cos(radiansHeading));
                process(turtle, list.iterator());
            }
        } else if (outOfYBounds(y)) {
            boolean temp = turtle.getPenDown();
            if (y > 0) {
                y -= height;
                list.clear();
                list.add(height/Math.sin(radiansHeading));
                process(turtle, list.iterator());
                turtle.setPenDown(false);
                turtle.setX(-height);
                turtle.setY(Math.tan(radiansHeading));
                turtle.setPenDown(temp);
                list.clear();
                list.add(y/Math.sin(radiansHeading));             
                process(turtle, list.iterator());          
            } else {
                y += height;
                list.clear();
                list.add(-height/Math.sin(radiansHeading));
                process(turtle, list.iterator());
                turtle.setPenDown(false);
                turtle.setX(height);
                turtle.setPenDown(temp);
                list.clear();
                list.add(y/Math.sin(radiansHeading));
                process(turtle, list.iterator());
            }
        }
        turtle.setX(x);
        turtle.setY(y);
        return distance;
    }
    
    private boolean outOfXBounds(double x) {
        x = Math.abs(x);
        return (x > width);
    }
    
    private boolean outOfYBounds(double y) {
        y = Math.abs(y);
        return (y > height);
    }

}
