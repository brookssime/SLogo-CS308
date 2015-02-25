package commands;

import java.util.List;

import application.Model;

public class Back extends Command {

	public Back(Model model) {
		super(model, 1);
	}

	@Override
	public List<Object> function(List<Object> args) {
		return new Forward(myModel).function(putDoubleInList(-(double)args.get(0)));
	}

}
