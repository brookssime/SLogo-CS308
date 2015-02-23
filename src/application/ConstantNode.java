package application;

import java.util.List;

public class ConstantNode extends EvaluatorNode {
	
	private Object myData;

	public ConstantNode(Object data) {
		myData = data;
	}

	@Override
	public Object evaluate(List<Object> args) {
		return myData;
	}

}
