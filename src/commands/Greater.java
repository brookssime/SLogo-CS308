package commands;

import java.util.List;

import application.Model;

public class Greater extends Command{

	public Greater(Model model) {
		super(model, 2);
	}

	@Override
	public List<Object> function(List<Object> args) {
		boolean greater = (double)args.get(0) > (double)args.get(1);
		double greatNum = -1;
		if (greater) greatNum = 1;
		return putDoubleInList(greatNum);
	}

}
