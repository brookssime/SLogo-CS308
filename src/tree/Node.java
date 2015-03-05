package tree;

import java.util.ArrayList;
import java.util.List;

abstract public class Node {

	public abstract List<Object> evaluate(List<Object> args);
	
	public List<Object> evaluate(){
		return evaluate(new ArrayList<>());
	}

	public abstract int countVariables();
	
	public abstract List<VariableNode> getVariableNodes();
}
