package commands;

import java.util.List;

import application.Model;

public class Not extends Command{

	public Not(Model model) {
		super(model, 1);
	}

	@Override
	public List<Object> function(List<Object> args) {
		double not = -(double)args.get(0);
		return putObjectInList(not);
	}

}
