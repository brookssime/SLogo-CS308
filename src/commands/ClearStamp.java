package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class ClearStamp extends CommandNode{

	public ClearStamp(Model myModel) {
		super(myModel, Double.class, Double.class);
	}

	@Override
	public List<Object> function(Turtle myTurtle, List<Object> args) {
		getModel().clearStampProperty().set(getModel().clearStampProperty().get());
		return putObjectInList(1);
	}

}