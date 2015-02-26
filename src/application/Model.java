package application;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import commands.UserCommand;

public class Model implements Observer{
	private Parser myParser;
    private Turtle myTurtle;
    private List<UserCommand> myUserCommands;
    private List<UserCommand> myHistory;
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
    
    public Map<String, Double> getVariableMap() {
        return variableMap;
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
