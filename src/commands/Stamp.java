package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class Stamp extends CommandNode{

	public Stamp(Model myModel) {
		super(myModel, Double.class, Double.class);
	}

	@Override
	public List<Object> function(Turtle myTurtle, List<Object> args) {
		myTurtle.stampProperty().set(!myTurtle.stampProperty().get());
		return putObjectInList(myTurtle.shapeProperty().get());
	}

}
