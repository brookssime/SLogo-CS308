package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import application.Node;
import application.Model;
import application.BlockNode;
import application.VariableNode;

public abstract class CommandNode extends Node {

	protected Model myModel;
	private List<Node> myChildren;
	protected int myArgNum;
	
	public CommandNode(Model myModel, int myArgNum){
		this.myModel = myModel;
		this.myArgNum = myArgNum;
		myChildren = new ArrayList<Node>();
	}
	
    /**
     * @param parameters
     *            collection of parameters needed to compute the given command
     * @return double result from executing the command
     */
    public List<Object> evaluate(List<Object> args){
        List<Object> cmdArgs = new ArrayList<Object>();
        for (Node child: myChildren){
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
    
    protected List<Node> getRootNodes(Object myUserCommandNode) {
        // Throw error if myUserCommandNode is not a UserCommandNode
        List<Object> rootObjectList = putObjectInList(myUserCommandNode);
        while(rootObjectList.get(0) instanceof BlockNode) {
            rootObjectList = ((BlockNode) rootObjectList.get(0)).evaluate();
        }
        List<Node> rootNodeList = new ArrayList<>();
        rootObjectList.stream().forEach(o -> rootNodeList.add((Node) o));
        return rootNodeList;
    }
    
    public void addChild(Node...child){
        myChildren.addAll(Arrays.asList(child));
    }
    
    @Override
    public int countVariables() {
        int sum = 0;
        for (Node child: myChildren){
            sum += child.countVariables();
        }
        return sum;
    }
    
    @Override
    public List<VariableNode> getVariableNodes() {
        List<VariableNode> variableList = new ArrayList<>();
        for (Node child : myChildren) {
            variableList.addAll(child.getVariableNodes());
        }
        return variableList;
    }

}
