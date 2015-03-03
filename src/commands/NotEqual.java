package commands;

import java.util.List;

import application.Model;

public class NotEqual extends Command{

	public NotEqual(Model model) {
		super(model, 2);
	}

	@Override
	public List<Object> function(List<Object> args) {
		double eqNum = (double)new Equal(myModel).function(args).get(0);
		return putObjectInList(-eqNum);
	}

}
