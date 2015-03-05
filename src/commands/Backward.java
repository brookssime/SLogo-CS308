package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class Backward extends CommandNode {

	public Backward(Model model) {
		super(model, 1);
	}

	@Override
	public List<Object> function(List<Object> args) {
		return new Forward(myModel).function(putObjectInList(-(double)args.get(0)));
	}

}
