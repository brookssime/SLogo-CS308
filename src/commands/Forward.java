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
    	double distance = (double) args.get(0);
        advanceTurtle(distance);
        return putDoubleInList(distance);
    }
	private void advanceTurtle(double distance) {
		maxX = myModel.getMaxX();
        maxY = myModel.getMaxY();
        Turtle turtle = myModel.getActiveTurtle();
        double radiansHeading = turtle.getRadiansHeading();
        double x = turtle.getX() + distance*Math.cos(radiansHeading);
        double y = turtle.getY() + distance*Math.sin(radiansHeading);
        boolean temp = turtle.isPenDown();
        if (outOfXBounds(x)) {
            double signX = Math.signum(x);
            double distX = signX*maxX - turtle.getX();
            double incrDist = distX/Math.cos(radiansHeading);
            double incrY = turtle.getY() + incrDist*Math.sin(radiansHeading);
            if(outOfYBounds(incrY)){
                double signY = Math.signum(y);
                double distY = signY*maxY - turtle.getY();
                incrDist = distY/Math.sin(radiansHeading);
                double incrX = turtle.getX() + incrDist*Math.cos(radiansHeading);
            	turtle.setLocation(incrX, signY*maxY);
            	turtle.setPenDown(false);
            	turtle.setY(-signY*maxY);
            	turtle.setPenDown(temp);
            	advanceTurtle(distance - incrDist);
            }
            else{
            	turtle.setLocation(signX*maxX, incrY);
            	turtle.setPenDown(false);
            	turtle.setX(-signX*maxX);
            	turtle.setPenDown(temp);
            	advanceTurtle(distance - incrDist);
            }
        }
        else if (outOfYBounds(y)){
            double signY = Math.signum(y);
            double distY = signY*maxY - turtle.getY();
            double incrDist = distY/Math.sin(radiansHeading);
            double incrX = turtle.getX() + incrDist*Math.cos(radiansHeading);
            turtle.setLocation(incrX, signY*maxY);
        	turtle.setPenDown(false);
        	turtle.setY(-signY*maxY);
        	turtle.setPenDown(temp);
        	advanceTurtle(distance - incrDist);
        }
        else{
        	turtle.setLocation(x, y);
        }
	}
    
    private boolean outOfXBounds(double x) {
        return (Math.abs(x) > maxX);
    }
    
    private boolean outOfYBounds(double y) {
        return (Math.abs(y) > maxY);
    }

}
