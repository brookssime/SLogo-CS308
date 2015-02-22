package application;

import java.util.ArrayList;
import java.util.List;

abstract class EvaluatorNode {

	abstract public Object evaluate(List<Object> args);
	
	public Object evaluate(){
		return evaluate(new ArrayList<>());
	}
}
