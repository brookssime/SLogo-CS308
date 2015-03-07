package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class TurtleState {
	
	protected Button state(){
		Button b = new Button("Turtle State");
		b.setOnAction(n -> StateOfTurtle());
		return b;
	}
	
	protected double TurtleX(){
		return 1.0;
	}
	protected double TurtleY(){
		return 1.0;
	}
	protected double TurtleDirection(){
		return 1.0;
	}
	protected String TurtlePen(){
		return "True";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected TableView createTable(){
		TableView table = new TableView();
		table.setEditable(false);
		TableColumn attributes = new TableColumn("Turtle Attributes");
		attributes.setMinWidth(125);
		TableColumn output = new TableColumn("Output");
		output.setMinWidth(120);
		table.getColumns().addAll(attributes, output);
		return table;
	}
	
	
	protected void StateOfTurtle(){
		Stage s = new Stage();
		s.setTitle("Turtle State");
		Group root = new Group();
		
		root.getChildren().add(createTable());
		Scene scene = new Scene(root);
		s.setScene(scene);
		s.show();
	
	}
}