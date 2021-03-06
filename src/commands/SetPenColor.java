package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class SetPenColor extends CommandNode{

	public SetPenColor(Model myModel) {
		super(myModel, Double.class, Double.class);
	}
	
	@Override
	public List<Object> function(Turtle myTurtle, List<Object> args) {
		double index = (double) args.get(0);
		getModel().penColorProperty().set(index);
		return putObjectInList(index);
	}

}