package application;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import commands.EvaluatorCommand;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import commands.UserCommand;

public class Model implements Observer{
	private Parser myParser;
    private Turtle myTurtle;
    private Map<String, EvaluatorCommand> myUserCommands;
    private List<EvaluatorCommand> myHistory;
    private double maxX;
    private double maxY;
    private Map<String, UserCommand> commandHistoryMap;
    private Map<String, Double> variableMap;
    private BooleanProperty clearScreen;
    
    public Model(double maxX, double maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        myTurtle = new Turtle();
        commandHistoryMap = new HashMap<String, UserCommand>();
        myParser = new Parser(this);

        myUserCommands = new HashMap<String, EvaluatorCommand>();
        myHistory = new ArrayList<>(); 
        clearScreen = new SimpleBooleanProperty();

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
    
    public double getVariableValue(String key) {
        return variableMap.get(key);
    }
    
    public void setVariableValue(String key, double value) {
        variableMap.put(key, value);
    }
    
    public void addUserCommand(String key, EvaluatorCommand value) {
        myUserCommands.put(key, value);
    }
    
    public BooleanProperty clearScreenProperty(){
    	return clearScreen;
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		EvaluatorCommand outputCmd;
		try {
			outputCmd = myParser.parse(arg1.toString());
			myHistory.add(outputCmd);
			outputCmd.process(null);
		} catch (InstantiationException | IllegalAccessException
				| InvocationTargetException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
