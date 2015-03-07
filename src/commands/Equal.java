package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class Equal extends CommandNode{

	public Equal(Model myModel) {
		super(myModel, Double.class, Double.class);
	}

	@Override
	public List<Object> function(Turtle myTurtle, List<Object> args) {
		boolean eq = (double)args.get(0) == (double)args.get(1);
		double eqNum = 0;
		if (eq) eqNum = 1;
		return putObjectInList(eqNum);
	}

}
