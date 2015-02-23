package application;

import java.util.List;

public class VariableNode extends EvaluatorNode {

	private int myIndex;
	
	public VariableNode(int index) {
		myIndex = index;
	}

	@Override
	public Object evaluate(List<Object> args) {
		return args.get(myIndex);
	}

}
