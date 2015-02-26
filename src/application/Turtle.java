package application;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

public class Turtle {
    private static final double FULL_ROTATION = 360;
    private static final double HALF_ROTATION = 180;
    private DoubleProperty heading = new SimpleDoubleProperty();
    private ObjectProperty<double[]> location = new SimpleObjectProperty<>();
    private BooleanProperty penDown = new SimpleBooleanProperty();
    private BooleanProperty showing = new SimpleBooleanProperty();
    private ObjectProperty<Node> node = new SimpleObjectProperty<>();

    public Turtle() {
    	heading = new SimpleDoubleProperty();
    	double[] coords = {0,0};
    	location.setValue(coords);
        penDown.setValue(false);
        showing.setValue(true);
    }

    public static double getFullRotation() {
        return FULL_ROTATION;
    }
    
    public static double getHalfRotation() {
        return HALF_ROTATION;
    }
    
    public DoubleProperty getHeadingProperty(){
    	return heading;
    }

    public double getHeading() {
        return heading.getValue();
    }

    public double getRadiansHeading() {
        return heading.getValue() * Math.PI / HALF_ROTATION;
    }

    public void setHeading(double heading) {
        this.heading.setValue(heading % FULL_ROTATION);
    }
    
    public Property<double[]> getLocationProperty(){
    	return location;
    }

    public double[] getLocation() {
        return location.getValue();
    }

    public double getX() {
        return location.getValue()[0];
    }

    public double getY() {
        return location.getValue()[1];
    }

    public double setLocation(double x, double y) {
        double oldX = location.getValue()[0];
        double oldY = location.getValue()[1];
        this.location.setValue(new double[] { x, y });
        return Math.sqrt(Math.pow((x - oldX), 2) + Math.pow((y - oldY), 2));
    }
    
    public BooleanProperty getPenDownProperty() {
        return penDown;
    }

    public boolean isPenDown() {
        return penDown.getValue();
    }

    public void setPenDown(boolean penDown) {
        this.penDown.setValue(penDown);
    }
    
    public BooleanProperty getShowingProperty() {
        return showing;
    }

    public boolean isShowing() {
        return showing.getValue();
    }

    public void setShowing(boolean showing) {
        this.showing.setValue(showing);
    }
    
    public Property<Node> getNodeProperty() {
        return node;
    }

    public Node getNode() {
        return node.getValue();
    }

    public void setNode(Node node) {
        this.node.setValue(node);
    }
}