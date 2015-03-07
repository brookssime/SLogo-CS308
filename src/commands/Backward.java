package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class Backward extends CommandNode {

	public Backward(Model model) {
		super(model, Double.class);
	}

	@Override
	public List<Object> function(Turtle myTurtle, List<Object> args) {
		return new Forward(getModel()).function(null, putObjectInList(-(double)args.get(0)));
	}

}
