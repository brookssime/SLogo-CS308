// This entire file is part of my masterpiece.
// COSETTE GOLDSTEIN

package view;
import java.util.HashMap;
import java.util.Map;



import javax.swing.Timer;
import application.Turtle;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
public class Display {
	public static final int SIZE_OF_TURTLE=30;
	public static final int OFFSET=375/2;
	public static final Integer[] LOCATION_OF_DISPLAY={200,100};
	private Group root;
	private Rectangle initial;
	private Color penColor;
	private ImageView turtleImage=new ImageView();
	private Image myImage;//=new Image();
	private boolean penDown;
	private Map<Turtle, ImageView> imageMap = new HashMap<Turtle, ImageView>();
	
	protected Group makeDisplay(int height,int width,Turtle t) {
		penColor=Color.BLACK;
		root=new Group();
		initial=new Rectangle(height,width);
		initial.setStroke(Color.BLACK);
		initial.setFill(Color.WHITE);
		myImage = new Image(getClass().getResourceAsStream("arrow.png"));
		ImageView turtle=addTurtle(height/2-SIZE_OF_TURTLE/2,width/2-SIZE_OF_TURTLE/2,t);//location so that the center of the turtle is on the center of the display
		imageMap.put(t, turtle); //put the first turtle in the map with itself as the key and the corresponding image as the value
		root.getChildren().addAll(initial,turtle);
		root.setLayoutX(LOCATION_OF_DISPLAY[0]);
		root.setLayoutY(LOCATION_OF_DISPLAY[1]);
		return root;
	}
	protected ImageView addTurtle(int xLocation, int yLocation,Turtle t) {
		ImageView turtleImage1=new ImageView();
		//Image image = new Image(getClass().getResourceAsStream("arrow.png"));
		turtleImage1.setFitHeight(SIZE_OF_TURTLE);
		turtleImage1.setFitWidth(SIZE_OF_TURTLE);
		turtleImage1.setImage(myImage);
		turtleImage1.relocate(xLocation,yLocation);
		ImageChooser imageChooser=new ImageChooser(this);
		turtleImage1.setOnMouseClicked(e->{imageChooser.start(new Stage(),false);turtleImage1.setImage(imageChooser.getImage());});
		return turtleImage1;
	}
	protected ImageView updateTurtleImage() {
		return turtleImage;
	}
	
	protected void setNewImage(Image image){
	    myImage= image;
	}
	
	protected void setImageForAllTurtles(Image image){
	    for (Turtle t:imageMap.keySet()){
	        ImageView newTurtleImage=new ImageView();
	        newTurtleImage.setImage(image);
	        imageMap.get(t).setImage(image);
	        //imageMap.replace(t, imageMap.get(t), );
	        //imageMap.replace
	    }
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
		root.getChildren().add(drawLines(imageMap.get(t).getLayoutX()+(SIZE_OF_TURTLE/2),imageMap.get(t).getLayoutY()+(SIZE_OF_TURTLE/2),newX+OFFSET,newY+OFFSET));
		imageMap.get(t).relocate(newX+OFFSET-(SIZE_OF_TURTLE/2),newY+OFFSET-(SIZE_OF_TURTLE/2));
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
	
	protected Group getRoot() {
		return root;
	}
}

