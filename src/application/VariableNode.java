package application;

import java.util.ArrayList;
import java.util.List;

public class VariableNode extends EvaluatorNode {

	private int myIndex;
	
	public VariableNode(int index) {
		myIndex = index;
	}

	@Override
	public List<Object> evaluate(List<Object> args) {
		List<Object> list = new ArrayList<>();
		list.add(args.get(myIndex));
		return list;
	}

	@Override
	public int countVariables() {
		return 1;
	}

}
