package view;

import application.Turtle;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
public class Display {
	private Group root;
	private Rectangle initial;
	//private Line l;
	private Color penColor;//=Color.BLACK;//needs to change to listener
	private ImageView turtleImage;
	private Turtle myTurtle;
	protected Group makeDisplay(int height,int width) {
		penColor=Color.BLACK;
		root=new Group();
		initial=new Rectangle(height,width);
		initial.setStroke(Color.BLACK);
		initial.setFill(Color.WHITE);
		ImageView turtle=addTurtle(height/2-15,width/2-15);
		root.getChildren().addAll(initial,turtle);
		root.setLayoutX(200);
		root.setLayoutY(100);
		return root;
	}
	private ImageView addTurtle(int xLocation, int yLocation) {
		turtleImage=new ImageView();
		Image image = new Image(getClass().getResourceAsStream("arrow.png"));
		turtleImage.setFitHeight(30);
        turtleImage.setFitWidth(30);
        turtleImage.setImage(image);
		turtleImage.setLayoutX(xLocation);
		turtleImage.setLayoutY(yLocation);
		return turtleImage;
	}
	protected ImageView updateTurtleImage() {
		return turtleImage;
	}
	
	protected void updateTurtleLocation(double newX, double newY) {
		root.getChildren().add(drawLines(turtleImage.getLayoutX(),turtleImage.getLayoutY(),newX,newY));
		turtleImage.setLayoutX(newX);
		turtleImage.setLayoutY(newY);
	}
	
	protected Line drawLines(double startX, double startY,double endX, double endY) {
		Line l=new Line();
		l.setStroke(penColor);
		l.setStartX(startX);
		l.setStartY(startY);
		l.setEndX(endX);
		l.setEndY(endY);
		l.setStrokeWidth(2);
		return l;
	}
	protected double getTurtX() {
		return turtleImage.getLayoutX();
	}
	protected double getTurtY() {
		return turtleImage.getLayoutY();
	}
	protected void setBackgroundFill(Color c) {
		initial.setFill(c);
	}
	protected void setLineFill(Color c) {
		penColor=c;
	}
}
