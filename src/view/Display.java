
package view;
import java.util.HashMap;
import java.util.Map;

import application.Turtle;
import javafx.scene.Group;
import javafx.scene.Node;
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
	private Turtle myTurtle;
	private boolean penDown;
	private double xLoc;
	private double yLoc;
	private Map<Turtle, ImageView> imageMap = new HashMap<Turtle, ImageView>();
	protected Group makeDisplay(int height,int width,Turtle t) {
		penColor=Color.BLACK;
		root=new Group();
		initial=new Rectangle(height,width);
		initial.setStroke(Color.BLACK);
		initial.setFill(Color.WHITE);
		ImageView turtle=addTurtle(height/2-SIZE_OF_TURTLE/2,width/2-SIZE_OF_TURTLE/2,t);//location so that the center of the turtle is on the center of the display
		imageMap.put(t, turtle);
		System.out.print(imageMap.containsKey(t));
		root.getChildren().addAll(initial,turtle);
		root.setLayoutX(LOCATION_OF_DISPLAY[0]);
		root.setLayoutY(LOCATION_OF_DISPLAY[1]);
		return root;
	}
	protected ImageView addTurtle(int xLocation, int yLocation,Turtle t) {
		ImageView turtleImage1=new ImageView();
		Image image = new Image(getClass().getResourceAsStream("arrow.png"));
		turtleImage1.setFitHeight(SIZE_OF_TURTLE);
		turtleImage1.setFitWidth(SIZE_OF_TURTLE);
		turtleImage1.setImage(image);
		turtleImage1.relocate(xLocation,yLocation);
		xLoc=turtleImage1.getLayoutX()+15;
		yLoc=turtleImage1.getLayoutY()+15;
		return turtleImage1;
	}
	protected ImageView updateTurtleImage() {
		return turtleImage;
	}
	
	protected void updateMap(Turtle t, ImageView w) {
		imageMap.put(t, w);
	}
	
	protected void addToRoot(Node n) {
		root.getChildren().add(n);
	}
	
	protected Map<Turtle,ImageView> getMap() {
		return imageMap;
	}
	
	protected void updateTurtleLocation(double newX, double newY,Turtle t) {
		root.getChildren().add(drawLines(imageMap.get(t).getLayoutX()+15,imageMap.get(t).getLayoutY()+15,newX+375/2,newY+375/2));
		imageMap.get(t).relocate(newX+375/2-15,newY+375/2-15);
		xLoc=newX+375/2;
		yLoc=newY+375/2;
	}
	protected void updateTurtleHeading(Number newValue,Turtle t) {
		imageMap.get(t).setRotate((double)newValue);
	}
	protected void updateTurtleShowing(boolean show,Turtle t) {
		imageMap.get(t).setVisible(show);
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

