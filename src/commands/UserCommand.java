package commands;

import java.util.ArrayList;
import java.util.List;

import application.Model;
import application.EvaluatorNode;

public class UserCommand extends Command {

	private List<EvaluatorNode> myNodes;
	
	public UserCommand(Model model, List<EvaluatorNode> nodeList){
		super(model, countVariables(nodeList));
		myNodes = nodeList;
	}

	private static int countVariables(List<EvaluatorNode> nodeList) {
		int sum = 0;
		for (EvaluatorNode node: nodeList){
			sum += node.countVariables();
		}
		return sum;
	}

	@Override
	public List<Object> function(List<Object> args) {
		List<Object> output = new ArrayList<>();
		for (EvaluatorNode node: myNodes){
			output.addAll(node.evaluate(args));
		}
		return output;
	}
	
}
