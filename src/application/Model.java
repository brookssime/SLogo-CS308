package application;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import exceptions.*;
import tree.CommandNode;
import tree.EvaluatorNode;
import view.View;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model implements Observer{
	private Parser myParser;
    private TurtleList myTurtleList;
    private Map<String, CommandNode> myUserCommands;
    private Map<String, CommandNode> myCommandHistory;
    private Map<String, Double> variableMap;
    private double maxX;
    private double maxY;
    private String myLanguage;
    private BooleanProperty clearScreen;
    private StringProperty errorMsg;
    
    public Model(double maxX, double maxY) {
    	myLanguage="English";
    	myParser = new Parser(this);
        myTurtleList = new TurtleList();
        myUserCommands = new HashMap<String, CommandNode>(); 
        myCommandHistory = new HashMap<String, CommandNode>();
        variableMap = new HashMap<String, Double>();
        this.maxX = maxX;
        this.maxY = maxY;
        clearScreen = new SimpleBooleanProperty();
        errorMsg = new SimpleStringProperty();
    }
    
    public void updateCommandPatterns() {
    	myParser.updateCommandPatterns();
    }

	public TurtleList getTurtleList() {
		return myTurtleList;
	}
    
    public void setLanguage(String language) {
    	myLanguage=language;
    	//this.language.bind(view.);
    }
    
    public String getLanguage() {
    	return myLanguage;
    }
    
    public double getMaxX() {
        return maxX;
    }
    
    public double getMaxY() {
        return maxY;
    }
    
    public Double getVariableValue(String key) {
        return variableMap.get(key);
    }
    
    public void setVariableValue(String key, double value) {
        variableMap.put(key, value);
    }
    
    public void removeVariableValue(String key) {
        variableMap.remove(key);
    }
    
    public void addUserCommand(String key, EvaluatorNode value) {
        myUserCommands.put(key, value);
    }
    
    public CommandNode getUserCommand(String key) {
        return myUserCommands.get(key);
    }
    
    public BooleanProperty clearScreenProperty(){
    	return clearScreen;
    }
    
    public StringProperty errorMessageProperty(){
    	return errorMsg;
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		CommandNode outputCmd;
		try {
			outputCmd = myParser.parse(arg1.toString());
			myCommandHistory.put(arg1.toString(), outputCmd);
			outputCmd.evaluate();
		} catch (InstantiationException | IllegalAccessException
				| InvocationTargetException | ClassNotFoundException | UnbalancedBracketsException | InvalidCommandException | IncorrectParametersException e) {
			errorMsg.set(e.getMessage());
		}
	}

}
