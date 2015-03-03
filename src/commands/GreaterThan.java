package commands;

import java.util.List;

import application.Model;

public class GreaterThan extends Command{

	public GreaterThan(Model model) {
		super(model, 2);
	}

	@Override
	public List<Object> function(List<Object> args) {
		if ((double)args.get(0) > (double)args.get(1)) {
		    return putObjectInList(1);
		}
		return putObjectInList(-1);
	}

}
