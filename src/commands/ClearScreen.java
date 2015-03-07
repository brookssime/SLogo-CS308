package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class ClearScreen extends CommandNode{

	public ClearScreen(Model myModel) {
		super(myModel, new Class[0]);
	}

	@Override
	public List<Object> function(Turtle myTurtle, List<Object> args) {
		getModel().clearScreenProperty().setValue(!getModel().clearScreenProperty().getValue());
		double dist = (double)new Home(getModel()).function(null, args).get(0);
		return putObjectInList(dist);
	}

}
