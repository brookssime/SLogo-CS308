package view;

import java.util.ArrayList;

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
	
	TurtleList tList = new TurtleList();
	public TurtleState (TurtleList t){
		tList = t;
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
	
	
	protected Button state(){
		Button b = new Button("Turtle State");
		b.setOnAction(n -> {
			attrib();
			StateOfTurtle();
			
		});
		return b;
	}
	protected Button refreshButton(){
		Button b = new Button ("Refresh");
		b.setOnAction(d -> refresh());
		return b;
	}
	
	
	protected void attrib(){
		for (Turtle l: tList.getActiveTurtles()){
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
		x.clear();
		y.clear();
		r.clear();
		isPen.clear();
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
		Scene scene = new Scene(root);
		t.setScene(scene);
		t.show();
	
	}
}