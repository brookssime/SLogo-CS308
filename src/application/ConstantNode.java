package application;

import java.util.ArrayList;
import java.util.List;

public class ConstantNode extends Node {
	
	private Object myData;

	public ConstantNode(Object data) {
		myData = data;
	}

	@Override
	public List<Object> evaluate(List<Object> args) {
		List<Object> list = new ArrayList<>();
		list.add(myData);
		return list;
	}

	@Override
	public int countVariables() {
		return 0;
	}

    @Override
    public List<VariableNode> getVariableNodes() {
        List<VariableNode> list = new ArrayList<>();
        return list;
    }

}
