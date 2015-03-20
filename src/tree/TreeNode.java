// This entire file is part of my masterpiece.
// Sean Scott

package tree;

import java.util.ArrayList;
import java.util.List;

import application.Turtle;

abstract public class TreeNode {

	public abstract List<Object> evaluate(Turtle myTurtle, List<Object> args);
	
	public List<Object> evaluate(){
		return evaluate(null, new ArrayList<>());
	}

	public abstract int countVariables();
	
	public abstract List<VariableNode> getVariableNodes();
}
