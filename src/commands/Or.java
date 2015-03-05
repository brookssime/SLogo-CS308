package commands;

import java.util.List;

import application.Model;

public class Or extends CommandNode{

	public Or(Model model) {
		super(model, 2);
	}

	@Override
	public List<Object> function(List<Object> args) {
		return putObjectInList(Math.max((double)args.get(0), (double)args.get(1)));
	}

}
