package application;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import commands.EvaluatorCommand;
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
    
    
    public Model(double maxX, double maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        myTurtle = new Turtle();
        commandHistoryMap = new HashMap<String, UserCommand>();
        myParser = new Parser(this);
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

	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			myHistory.add(myParser.parse(arg1.toString()));
		} catch (InstantiationException | IllegalAccessException
				| InvocationTargetException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
