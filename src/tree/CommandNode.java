package tree;

import java.util.ArrayList;
import java.util.List;

import exceptions.IncorrectParametersException;
import application.Model;

public abstract class CommandNode extends TreeNode {

	private Model myModel;
    private List<TreeNode> myChildren;
	private int myArgNum;
	private Class[] myParameterArray;
	
	public CommandNode(Model myModel, Class...myParameterArray){
		this.myModel = myModel;
		this.myParameterArray = myParameterArray;
		this.myArgNum = myParameterArray.length;
		myChildren = new ArrayList<TreeNode>();
	}
	
    /**
     * @param parameters
     *            collection of parameters needed to compute the given command
     * @return double result from executing the command
     */
    public List<Object> evaluate(List<Object> args){
        List<Object> cmdArgs = new ArrayList<Object>();
        for (TreeNode child: myChildren){
            cmdArgs.addAll(child.evaluate(args));
        }
        if (!checkParameters(cmdArgs, myParameterArray)) {
            throw new IncorrectParametersException(this.getClass().getName());
        };
        return function(cmdArgs);
    }
    
    protected abstract List<Object> function(List<Object> args);
    
    protected List<Object> putObjectInList(Object o) {
        List<Object> list = new ArrayList<>();
        list.add(o);
        return list;
    }
    
    protected Model getModel() {
        return myModel;
    }
    
    public int getArgNum() {
        return myArgNum;
    }
    
    protected void setArgNum(int myArgNum) {
        this.myArgNum = myArgNum;
    }
    
    protected List<TreeNode> getRootNodes(Object myBlockNode) {
        List<Object> rootObjectList = putObjectInList(myBlockNode);
        while(rootObjectList.size() > 0 && rootObjectList.get(0) instanceof BlockNode) {
            rootObjectList = ((BlockNode) rootObjectList.get(0)).evaluate();
        }
        List<TreeNode> rootNodeList = new ArrayList<>();
        rootObjectList.stream().forEach(o -> rootNodeList.add((TreeNode) o));
        return rootNodeList;
    }
    
    public void addChild(TreeNode child){
        myChildren.add(child);
    }
    
    @Override
    public int countVariables() {
        int sum = 0;
        for (TreeNode child: myChildren){
            sum += child.countVariables();
        }
        return sum;
    }
    
    @Override
    public List<VariableNode> getVariableNodes() {
        List<VariableNode> variableList = new ArrayList<>();
        for (TreeNode child : myChildren) {
            variableList.addAll(child.getVariableNodes());
        }
        return variableList;
    }
    
    /*
     * Checks the parameters of a command's evaluate function
     * 
     * Each element in args corresponds to a Class object in myClass
     * 
     * return false if the parameters in args are not the correct type
     */
    protected boolean checkParameters(List<Object> args, Class...myParameterArray) {
        if (args.size() != myParameterArray.length) {
            return false;
        }
        for (int i = 0; i < args.size(); i++) {
            if (!args.get(i).getClass().equals(myParameterArray[i])) {
                return false;
            }
        }
        return true;
    }
    
    protected void setParameterArray(Class...myParameterArray) {
            this.myParameterArray = myParameterArray;
    }

}
