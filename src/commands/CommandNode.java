package commands;

import java.util.ArrayList;
import java.util.List;

import application.EvaluatorNode;
import application.Model;
import application.UserCommandNode;
import application.VariableNode;

public abstract class CommandNode extends EvaluatorNode {

	protected Model myModel;
	private List<EvaluatorNode> myChildren;
	protected int myArgNum;
	
	public CommandNode(Model myModel, int myArgNum){
		this.myModel = myModel;
		this.myArgNum = myArgNum;
		myChildren = new ArrayList<EvaluatorNode>();
	}
	
    /**
     * @param parameters
     *            collection of parameters needed to compute the given command
     * @return double result from executing the command
     */
    public List<Object> evaluate(List<Object> args){
        List<Object> cmdArgs = new ArrayList<Object>();
        for (EvaluatorNode child: myChildren){
            cmdArgs.addAll(child.evaluate(args));
        }
        return function(cmdArgs);
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
    
    public void addChild(EvaluatorNode child){
        myChildren.add(child);
    }
    
    @Override
    public int countVariables() {
        int sum = 0;
        for (EvaluatorNode child: myChildren){
            sum += child.countVariables();
        }
        return sum;
    }
    
    @Override
    public List<VariableNode> getVariableNodes() {
        List<VariableNode> variableList = new ArrayList<>();
        for (EvaluatorNode child : myChildren) {
            variableList.addAll(child.getVariableNodes());
        }
        return variableList;
    }

}
