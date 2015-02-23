package view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Display {
	private Group root;
	private Rectangle initial;
	private Line l;
	private Color penColor=Color.AQUA;//needs to change to listener
	
	protected Group makeDisplay(int height,int width) {
        root=new Group();
        initial=new Rectangle(height,width);
        initial.setStroke(Color.BLACK);
        initial.setFill(Color.WHITE);
		l=new Line();
		l.setFill(penColor);
        
        Shape turtle=addTurtle(100,10);
        //Line line=drawLines(turtle.getLayoutX()+5,turtle.getLayoutY()+5,20);
        root.getChildren().addAll(initial,drawLines(turtle.getLayoutX()+5,turtle.getLayoutY()+5,20),turtle);
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
		return s;
	}
	
	private Line drawLines(double startX, double startY,int length) {
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

	protected void setLineFill(Color c) {
		l.setFill(c);
		//l.setFill(c);
	}
	
}
