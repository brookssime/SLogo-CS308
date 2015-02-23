package commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import application.Model;
import application.Turtle;

public class Forward extends Command {    
    private double maxX;
    private double maxY;

    public Forward() {
        super(1);
    }
    @Override
    public double function(Model model, List<Object> parameters) {
        maxX = model.getMaxX();
        maxY = model.getMaxY();
        Turtle turtle = model.getActiveTurtle();
        double distance = (double) parameters.get(0);
        double radiansHeading = turtle.getRadiansHeading();
        double x = turtle.getX() + distance*Math.cos(radiansHeading);
        double y = turtle.getY() + distance*Math.sin(radiansHeading);
        List<Object> list = new ArrayList<>();
        if (outOfXBounds(x)) {
            boolean temp = turtle.isPenDown();
            if (x > 0) {
                x -= maxX;
                list.add(maxX/Math.cos(radiansHeading));
                process(model, list);
                turtle.setPenDown(false);
                turtle.setX(-maxX);
                turtle.setPenDown(temp);
                list.clear();
                list.add(x/Math.cos(radiansHeading));
                process(model, list);
            } else {
                x += maxX;
                list.clear();
                list.add(-maxX/Math.cos(radiansHeading));
                process(model, list);
                turtle.setPenDown(false);
                turtle.setX(maxX);
                turtle.setPenDown(temp);
                list.clear();
                list.add(x/Math.cos(radiansHeading));
                process(model, list);
            }
        } else if (outOfYBounds(y)) {
            boolean temp = turtle.isPenDown();
            if (y > 0) {
                y -= maxY;
                list.clear();
                list.add(maxY/Math.sin(radiansHeading));
                process(model, list);
                turtle.setPenDown(false);
                turtle.setX(-maxY);
                turtle.setY(Math.tan(radiansHeading));
                turtle.setPenDown(temp);
                list.clear();
                list.add(y/Math.sin(radiansHeading));             
                process(model, list);          
            } else {
                y += maxY;
                list.clear();
                list.add(-maxY/Math.sin(radiansHeading));
                process(model, list);
                turtle.setPenDown(false);
                turtle.setX(maxY);
                turtle.setPenDown(temp);
                list.clear();
                list.add(y/Math.sin(radiansHeading));
                process(model, list);
            }
        }
        turtle.setX(x);
        turtle.setY(y);
        return distance;
    }
    
    private boolean outOfXBounds(double x) {
        return (Math.abs(x) > maxX);
    }
    
    private boolean outOfYBounds(double y) {
        return (Math.abs(y) > maxY);
    }

}
