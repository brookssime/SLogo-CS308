package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class SetPallet extends CommandNode{

	public SetPallet(Model myModel) {
		super(myModel, Double.class, Double.class);
	}

	@Override
	public List<Object> function(Turtle myTurtle, List<Object> args) {
		double[] pallet = { (double) args.get(0), (double) args.get(1), (double) args.get(2), (double) args.get(3) };
		getModel().palletProperty().set(pallet);
		return putObjectInList(pallet[0]);
	}

}