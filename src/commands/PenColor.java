package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class PenColor extends CommandNode{

	public PenColor(Model myModel) {
		super(myModel, Double.class, Double.class);
	}

	@Override
	public List<Object> function(Turtle myTurtle, List<Object> args) {
		return putObjectInList(getModel().penColorProperty().get());
	}

}