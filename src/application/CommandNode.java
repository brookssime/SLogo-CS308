package application;

import java.util.ArrayList;
import java.util.List;

import commands.Command;

public class CommandNode extends EvaluatorNode {
	
	private Command myCommand;
	private Model myModel;
	private List<EvaluatorNode> myChildren;

	public CommandNode(Command cmd, Model model){
		myModel = model;
		new CommandNode(new ArrayList<EvaluatorNode>(), cmd);
	}

	public CommandNode(ArrayList<EvaluatorNode> nodeList, Command cmd) {
		myChildren = nodeList;
		myCommand = cmd;
	}

	@Override
	public Object evaluate(List<Object> args) {
		List<Object> cmdArgs = new ArrayList<Object>();
		for (EvaluatorNode child: myChildren){
			cmdArgs.add(child.evaluate(args));
		}
		return myCommand.process(myModel, cmdArgs);
	}
	
	public void addChild(EvaluatorNode child){
		myChildren.add(child);
	}

}
