package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class ClearScreen extends CommandNode{

	public ClearScreen(Model myModel) {
		super(myModel, 0);
	}

	@Override
	public List<Object> function(List<Object> args) {
		myModel.clearScreenProperty().setValue(!myModel.clearScreenProperty().getValue());
		double dist = (double)new Home(myModel).function(args).get(0);
		return putObjectInList(dist);
	}

}
