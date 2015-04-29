// This entire file is part of my masterpiece. // Brooks Sime

package view;

import java.util.ArrayList;

import application.Model;
import application.Turtle;
import application.TurtleList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TurtleState {
	
	Model myModel;
	public TurtleState (Model m){
		myModel = m;
	}
	private Stage t = new Stage();
	
	private Label turtleX = new Label("TurtleX");
	private Label turtleY = new Label("TurtleY");
	private Label rotation = new Label("Rotation");
	private Label pen = new Label("Pen Down?");
	private ArrayList<Double> x = new ArrayList<Double>();
	private ArrayList<Double> y = new ArrayList<Double>();
	private ArrayList<Double> r = new ArrayList<Double>();
	private ArrayList<Boolean> isPen = new ArrayList<Boolean>();
	
	
	
	protected Button refreshButton(){
		Button b = new Button ("Refresh");
		b.setOnAction(d -> refresh());
		return b;
	}
	
	protected void initiateState(){
		attrib();
		StateOfTurtle();
	}
	
	protected void attrib(){
		System.out.println(myModel);
		for (Turtle l: myModel.getTurtleList().getActiveTurtles()){
			double turtX = l.getX();
			double turtY = l.getY();
			double rot = l.getHeading();
			boolean p = l.isPenDown();
			x.add(turtX);
			y.add(turtY);
			r.add(rot);
			isPen.add(p);
		}
		
	}
	protected void refresh(){
		x = new ArrayList<Double>();
		y = new ArrayList<Double>();
		r = new ArrayList<Double>();
		isPen = new ArrayList<Boolean>();
		t.close();
		
	}
	
	private GridPane output(){
		GridPane out = new GridPane();
		out.setPrefWidth(400);
		out.setVgap(10);
		out.setHgap(10);
		out.addRow(0, turtleX, new Label(x.toString()));
		out.addRow(1, turtleY, new Label(y.toString()));
		out.addRow(2, rotation, new Label(r.toString()));
		out.addRow(3, pen, new Label(isPen.toString()));
		return out;
	}
	
	
	
	protected void StateOfTurtle(){
		t.setTitle("Turtle State");
		Group root = new Group();
		VBox v = new VBox();
		v.getChildren().addAll(output(), refreshButton());
		root.getChildren().addAll(v);
		root.scaleXProperty();
		Scene scene = new Scene(root);
		t.setScene(scene);
		t.show();
		
	
	}
}