package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class TurtleState {
	
	protected Button button(){
	Button b = new Button("Turtle State");
	b.setOnAction(e -> StateOfTurtle());
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
	
	protected void StateOfTurtle(){
		Stage s = new Stage();
		s.setTitle("Turtle State");
		Group root = new Group();
		TableView table = new TableView();
		table.setEditable(false);
		TableColumn attributes = new TableColumn("Turtle Attributes");
		attributes.setMinWidth(125);
		TableColumn output = new TableColumn("Output");
		output.setMinWidth(120);
		table.getColumns().addAll(attributes, output);
		root.getChildren().add(table);
		Scene scene = new Scene(root);
		s.setScene(scene);
		s.show();
	
	}
}