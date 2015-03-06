package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class GreaterThan extends CommandNode{

	public GreaterThan(Model model) {
		super(model, Double.class, Double.class);
	}

	@Override
	public List<Object> function(List<Object> args) {
		if ((double)args.get(0) > (double)args.get(1)) {
		    return putObjectInList(1);
		}
		return putObjectInList((double) -1);
	}

}
