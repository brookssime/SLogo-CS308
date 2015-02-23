package application;

import javafx.scene.Node;

public class Turtle {
    private static double FULL_ROTATION = 360;
    private static double HALF_ROTATION = 180;
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

    public static double getFullRotation() {
        return FULL_ROTATION;
    }
    
    public static double getHalfRotation() {
        return HALF_ROTATION;
    }

    public double getHeading() {
        return heading;
    }

    public double getRadiansHeading() {
        return heading * Math.PI / HALF_ROTATION;
    }

    public void setHeading(double heading) {
        this.heading = heading % FULL_ROTATION;
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
        this.location = new double[] { x, y };
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