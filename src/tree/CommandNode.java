package tree;

import java.util.ArrayList;
import java.util.List;

import exceptions.IncorrectParametersException;
import application.Model;
import application.Turtle;

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
    public List<Object> evaluate(Turtle myTurtle, List<Object> args){
        List<Turtle> myTurtles = new ArrayList<>();
        if (myTurtle == null) {
            myTurtles = myModel.getTurtleList().getActiveTurtles();
        } else {
            myTurtles.add(myTurtle);
        }
        return evaluateTurtles(myTurtles, args);
    }
    
    private List<Object> evaluateTurtles(List<Turtle> myTurtles, List<Object> args) {
        List<Object> result = new ArrayList<>();
        for (Turtle myTurtle : myTurtles) {
            List<Object> cmdArgs = new ArrayList<Object>();
            for (TreeNode child : myChildren) {
                cmdArgs.addAll(child.evaluate(myTurtle, args));
            }
            checkParameters(cmdArgs, myParameterArray);
            result = function(myTurtle, cmdArgs);
        }
        return result;
    }
    
    protected abstract List<Object> function(Turtle myTurtle, List<Object> args);
    
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
    protected void checkParameters(List<Object> args, Class...myParameterArray) {
        if (args.size() != myParameterArray.length) {
            throwParametersException();
        }
        for (int i = 0; i < args.size(); i++) {
            if (!args.get(i).getClass().equals(myParameterArray[i])) {
                throwParametersException();
            }
        }
    }
    
    protected void setParameterArray(Class...myParameterArray) {
            this.myParameterArray = myParameterArray;
    }
    
    protected Class[] generateClassArray(Class c, int n) {
        Class[] array = new Class[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = c;
        }
        return array;
    }
    
    private void throwParametersException() {
        throw new IncorrectParametersException(this.getClass().getName());
    }
    
    protected List<Double> blockToDoubleList(BlockNode myBlockNode) {
        List<Object> turtleIDList= new ArrayList<>();
        getRootNodes(myBlockNode).stream().forEach(node -> turtleIDList.addAll(node.evaluate()));
        checkParameters(turtleIDList, generateClassArray(Double.class, turtleIDList.size()));
        List<Double> doubleList = new ArrayList<>();
        turtleIDList.stream().forEach(o -> doubleList.add((Double) o));
        return doubleList;
    }

}
