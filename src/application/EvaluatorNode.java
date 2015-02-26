package application;

import java.util.ArrayList;
import java.util.List;

abstract public class EvaluatorNode {

	abstract public List<Object> evaluate(List<Object> args);
	
	public List<Object> evaluate(){
		return evaluate(new ArrayList<>());
	}

	abstract public int countVariables();
	public abstract List<VariableNode> getVariableNodes();
}
