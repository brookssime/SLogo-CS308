package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class Equal extends CommandNode{

	public Equal(Model model) {
		super(model, Double.class, Double.class);
	}

	@Override
	public List<Object> function(List<Object> args) {
		boolean eq = (double)args.get(0) == (double)args.get(1);
		double eqNum = 0;
		if (eq) eqNum = 1;
		return putObjectInList(eqNum);
	}

}
