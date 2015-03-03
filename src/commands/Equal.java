package commands;

import java.util.List;

import application.Model;

public class Equal extends Command{

	public Equal(Model model) {
		super(model, 2);
	}

	@Override
	public List<Object> function(List<Object> args) {
		boolean eq = (double)args.get(0) == (double)args.get(1);
		double eqNum = -1;
		if (eq) eqNum = 1;
		return putObjectInList(eqNum);
	}

}
