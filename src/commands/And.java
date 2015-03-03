package commands;

import java.util.List;

import application.Model;

public class And extends Command{

	public And(Model model) {
		super(model, 2);
	}

	@Override
	public List<Object> function(List<Object> args) {
		return putObjectInList(Math.min((double)args.get(0), (double)args.get(1)));
	}

}
