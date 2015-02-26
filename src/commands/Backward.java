package commands;

import java.util.List;

import application.Model;

public class Backward extends Command {

	public Backward(Model model) {
		super(model, 1);
	}

	@Override
	public List<Object> function(List<Object> args) {
		return new Forward(myModel).function(putDoubleInList(-(double)args.get(0)));
	}

}
