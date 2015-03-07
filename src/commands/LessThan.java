package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class LessThan extends CommandNode{

	public LessThan(Model myModel) {
		super(null, Double.class, Double.class);
	}

	@Override
	public List<Object> function(Turtle myTurtle, List<Object> args) {
		if ((double)args.get(0) < (double)args.get(1)) {
		    return putObjectInList((double) 1);
		}
		return putObjectInList((double) -1);
	}

}
