package application;

import java.util.HashMap;
import java.util.Map;

import commands.UserCommand;

public class Model {
    private Turtle myTurtle;
    private double maxX;
    private double maxY;
    private Map<String, UserCommand> commandHistoryMap;
    private Map<String, Double> variableMap;
    
    public Model(double maxX, double maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        myTurtle = new Turtle();
        commandHistoryMap = new HashMap<String, UserCommand>();
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
    
    
}
