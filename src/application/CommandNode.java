package application;

import java.util.ArrayList;
import java.util.List;

import commands.Command;

public class CommandNode extends EvaluatorNode {
	
	private Command myCommand;
	private List<EvaluatorNode> myChildren;
	private int maxChildren;

	public CommandNode(Command command){
	    myChildren = new ArrayList<EvaluatorNode>();
	    myCommand = command;
	    maxChildren = command.getArgNum();
	}

	@Override
	public List<Object> evaluate(List<Object> args) {
		List<Object> cmdArgs = new ArrayList<Object>();
		for (EvaluatorNode child: myChildren){
			cmdArgs.addAll(child.evaluate(args));
		}
		return myCommand.process(cmdArgs);
	}
	
	public void addChild(EvaluatorNode child){
		myChildren.add(child);
	}

	@Override
	public int countVariables() {
		int sum = 0;
		for (EvaluatorNode child: myChildren){
			sum += child.countVariables();
		}
		return sum;
	}
	
	public int getMaxChildren() {
	    return maxChildren;
	}

}
