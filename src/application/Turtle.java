package application;

import javafx.scene.Node;

public class Turtle {
    private double heading;
    private double[] location = new double[2];
    private boolean penDown;
    private boolean showing;
    private Node node;

    public Turtle() {
        location[0] = 0;
        location[1] = 0;
        penDown = false;
        showing = true;
    }

    public double getHeading() {
        return heading;
    }

    public double getRadiansHeading() {
        return heading / (2 * Math.PI);
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public double[] getLocation() {
        return location;
    }
    
    public double getX() {
        return location[0];
    }
    
    public void setX(double x) {
        location[0] = x;
    }
    
    public void setY(double y) {
        location[1] = y;
    }
    
    public double getY() {
        return location[1];
    }

    public void setLocation(double x, double y) {
        this.location = new double[] {x, y};
    }

    public boolean isPenDown() {
        return penDown;
    }

    public void setPenDown(boolean penDown) {
        this.penDown = penDown;
    }

    public boolean isShowing() {
        return showing;
    }

    public void setShowing(boolean showing) {
        this.showing = showing;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}