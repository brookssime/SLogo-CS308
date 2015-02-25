package commands;

import java.util.List;

import application.Model;

public class Less extends Command{

	public Less(Model model) {
		super(model, 2);
	}

	@Override
	public List<Object> function(List<Object> args) {
		boolean less = (double)args.get(0) < (double)args.get(1);
		double lessNum = -1;
		if (less) lessNum = 1;
		return putDoubleInList(lessNum);
	}

}
