package application;
<<<<<<< HEAD
import javafx.scene.Node;


public class Turtle {
    private double heading;
    private double x;
    private double y;
    private boolean penDown;
    private boolean showing;
    private Node node;
    
    public Turtle() {
        x = 0;
        y = 0;
        penDown = false;
        showing = true;
    }

    public double getHeading() {
        return heading;
    }
    
    public double getRadiansHeading() {
        return heading/(2*Math.PI);
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
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
    
    
=======

public class Turtle {

>>>>>>> 01c1215472cb89159c56d9ebb3764b1788318997
}
