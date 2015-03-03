package commands;

import java.util.ArrayList;
import java.util.List;

import application.EvaluatorNode;
import application.Model;
import application.UserCommandNode;

public abstract class Command {

	protected Model myModel;
	protected int myArgNum;
	
	public Command(Model myModel, int myArgNum){
		this.myModel = myModel;
		this.myArgNum = myArgNum;
	}
	
    /**
     * @param parameters
     *            collection of parameters needed to compute the given command
     * @return double result from executing the command
     */
    public List<Object> process(List<Object> args){
    	//throw arg number Exceptions
    	return function(args);
    }
    
    protected abstract List<Object> function(List<Object> args);
    
    protected List<Object> putObjectInList(Object o) {
        List<Object> list = new ArrayList<>();
        list.add(o);
        return list;
    }
    
    public int getArgNum() {
        return myArgNum;
    }
    
    protected List<EvaluatorNode> getRootNodes(Object myUserCommandNode) {
        // Throw error if myUserCommandNode is not a UserCommandNode
        List<Object> rootObjectList = putObjectInList(myUserCommandNode);
        while(rootObjectList.get(0) instanceof UserCommandNode) {
            rootObjectList = ((UserCommandNode) rootObjectList.get(0)).evaluate();
        }
        List<EvaluatorNode> rootNodeList = new ArrayList<>();
        rootObjectList.stream().forEach(o -> rootNodeList.add((EvaluatorNode) o));
        return rootNodeList;
    }

}
