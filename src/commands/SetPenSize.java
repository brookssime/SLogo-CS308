package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class SetPenSize extends CommandNode{

	public SetPenSize(Model myModel) {
		super(myModel, Double.class);
	}

	@Override
	public List<Object> function(Turtle myTurtle, List<Object> args) {
		double pixels = (double) args.get(0);
		getModel().penSizeProperty().set(pixels);
		return putObjectInList(pixels);
	}

}