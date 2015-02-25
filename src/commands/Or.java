package commands;

import java.util.List;

import application.Model;

public class Or extends Command{

	public Or(Model model) {
		super(model, 2);
	}

	@Override
	public List<Object> function(List<Object> args) {
		return putDoubleInList(Math.max((double)args.get(0), (double)args.get(1)));
	}

}