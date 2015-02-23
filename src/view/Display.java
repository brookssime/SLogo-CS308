package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Display {
	private Group root;
	private Rectangle initial;
	private Color penColor=Color.AQUA;//needs to change to listener
	
	protected Group makeDisplay(int height,int width) {
        root=new Group();
        initial=new Rectangle(height,width);
        initial.setStroke(Color.BLACK);
        initial.setFill(Color.WHITE);
        
        Shape turtle=addTurtle(100,10);
        Line line=drawLines(turtle.getLayoutX()+5,turtle.getLayoutY()+5,20);
        root.getChildren().addAll(initial,line,turtle);
        root.setLayoutX(200);
        root.setLayoutY(100);
        return root;
        //Scene scene = new Scene(root, 400,400);
	}
	
	private Shape addTurtle(int xLocation, int yLocation) {
		Shape s=new Rectangle(10,10);
		s.setFill(Color.CORAL);
		s.setLayoutX(xLocation);
		s.setLayoutY(yLocation);
		//root.getChildren().add(s);
		return s;
	}
	
	private Line drawLines(double startX, double startY,int length) {
		Line l=new Line();
		l.setStartX(startX);
		l.setStartY(startY);
		l.setEndX(startX+length);
		l.setEndY(startY+(length*2));
		l.setFill(penColor);
		return l;
	}
	
	protected void setBackgroundFill(Color c) {
		initial.setFill(c);
	}
	
}
