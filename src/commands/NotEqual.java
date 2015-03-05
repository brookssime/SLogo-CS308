package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class NotEqual extends CommandNode{

	public NotEqual(Model model) {
		super(model, 2);
	}

	@Override
	public List<Object> function(List<Object> args) {
		double eqNum = (double)new Equal(myModel).function(args).get(0);
		return putObjectInList(-eqNum);
	}

}
