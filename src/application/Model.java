package application;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import view.View;
import commands.EvaluatorCommand;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import commands.UserCommand;

public class Model implements Observer{
	private Parser myParser;
    private Turtle myTurtle;
    private Map<String, EvaluatorCommand> myUserCommands;
    private Map<String, EvaluatorCommand> myCommandHistory;
    private Map<String, Double> variableMap;
    private double maxX;
    private double maxY;
    private String myLanguage;
    private BooleanProperty clearScreen;
    private StringProperty language;
    private View view;
    
    public Model(double maxX, double maxY) {
    	myLanguage="English";
    	myParser = new Parser(this);
        myTurtle = new Turtle();
        myUserCommands = new HashMap<String, EvaluatorCommand>(); 
        myCommandHistory = new HashMap<String, EvaluatorCommand>();
        variableMap = new HashMap<String, Double>();
        this.maxX = maxX;
        this.maxY = maxY;
        clearScreen = new SimpleBooleanProperty();
        
    }
    
    public void updateCommandPatterns() {
    	myParser.updateCommandPatterns();
    }
    
    public void setLanguage(String language) {
    	myLanguage=language;
    	//this.language.bind(view.);
    }
    
    public String getLanguage() {
    	return myLanguage;
    }
    
    public Turtle getActiveTurtle() {
        return myTurtle;
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
    
    public void addUserCommand(String key, EvaluatorCommand value) {
        myUserCommands.put(key, value);
    }
    
    public EvaluatorCommand getUserCommand(String key) {
        return myUserCommands.get(key);
    }
    
    public BooleanProperty clearScreenProperty(){
    	return clearScreen;
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		EvaluatorCommand outputCmd;
		try {
			outputCmd = myParser.parse(arg1.toString());
			myCommandHistory.put(arg1.toString(), outputCmd);
			outputCmd.process(null);
		} catch (InstantiationException | IllegalAccessException
				| InvocationTargetException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
