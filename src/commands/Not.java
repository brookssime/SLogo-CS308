package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class Not extends CommandNode{

	public Not(Model model) {
		super(model, double.class);
	}

	@Override
	public List<Object> function(List<Object> args) {
		if ((double) args.get(0) == (double) 0) {
		    return putObjectInList((double) 1);
		}
		return putObjectInList(0);
	}

}
