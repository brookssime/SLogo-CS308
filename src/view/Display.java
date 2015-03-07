package view;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import application.Turtle;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Display {
	
	public static final int SIZE_OF_TURTLE=30;
	public static final Integer[] LOCATION_OF_DISPLAY={200,100};
	
	private Group root;
	private Rectangle initial;
	//private Line l;
	private Color penColor;//=Color.BLACK;//needs to change to listener
	private ImageView turtleImage=new ImageView();
	//private Turtle myTurtle;
	private boolean penDown;
	private double xLoc;
	private double yLoc;
	private Map<Turtle, ImageView> imageMap = new HashMap<Turtle, ImageView>();
	//private Map<Double, Turtle> TurtleMap = new HashMap<Double, Turtle>();
	
	
	protected Group makeDisplay(int height,int width,Turtle t) {
		penColor=Color.BLACK;
		root=new Group();
		initial=new Rectangle(height,width);
		initial.setStroke(Color.BLACK);
		initial.setFill(Color.WHITE);
		ImageView turtle=addTurtle(height/2-SIZE_OF_TURTLE/2,width/2-SIZE_OF_TURTLE/2,t);//location so that the center of the turtle is on the center of the display
		root.getChildren().addAll(initial,turtle);
		root.setLayoutX(LOCATION_OF_DISPLAY[0]);
		root.setLayoutY(LOCATION_OF_DISPLAY[1]);
		return root;
	}
	protected ImageView addTurtle(int xLocation, int yLocation,Turtle turtle) {
		
		ImageView turtleImage1=turtleImage;
		if (!(imageMap.containsKey(turtle)))
			imageMap.put(turtle, turtleImage1);
		
		Image image = new Image(getClass().getResourceAsStream("arrow.png"));
		turtleImage1.setFitHeight(SIZE_OF_TURTLE);
        turtleImage1.setFitWidth(SIZE_OF_TURTLE);
        turtleImage1.setImage(image);
        turtleImage1.relocate(xLocation,yLocation);
        xLoc=turtleImage1.getLayoutX()+15;
        yLoc=turtleImage1.getLayoutY()+15;
		return turtleImage1;
	}
	protected Collection<ImageView> updateTurtleImage() {
		return imageMap.values();
	}
	
	protected void updateTurtleLocation(double newX, double newY,Turtle turtle) {
		root.getChildren().add(drawLines(xLoc,yLoc,newX+375/2,newY+375/2));
		imageMap.get(turtle).relocate(newX+375/2,newY+375/2);
		
		//turtleImage.relocate(newX+375/2,newY+375/2);
		xLoc=newX+375/2;
		yLoc=newY+375/2;
	}
	
	protected void updateTurtleHeading(Number newValue) {
		turtleImage.setRotate((double)newValue);
	}
	
	protected void updateTurtleShowing(boolean show) {
		turtleImage.setVisible(show);
	}
	
	protected Line drawLines(double startX, double startY,double endX, double endY) {
		Line l=new Line();
		l.setStroke(penColor);
		l.setStartX(startX);
		l.setStartY(startY);
		l.setEndX(endX);
		l.setEndY(endY);
		l.setStrokeWidth(2);
		l.setVisible(penDown);
		return l;
	}
	
	protected Group getRoot() {
		return root;
	}
	
	protected void setVisibility(boolean pen) {
		penDown=pen;
	}
	
	protected void setBackgroundFill(Color c) {
		initial.setFill(c);
	}
	protected void setLineFill(Color c) {
		penColor=c;
	}
}
