package tree;

import java.util.ArrayList;
import java.util.List;

import application.Turtle;

public class ConstantNode extends TreeNode {
	
	private Object myData;

	public ConstantNode(Object data) {
		myData = data;
	}

	@Override
	public List<Object> evaluate(Turtle myTurtle, List<Object> args) {
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
