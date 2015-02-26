package application;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
    private BooleanProperty clearScreen;
    
    public Model(double maxX, double maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        myTurtle = new Turtle();
        commandHistoryMap = new HashMap<String, UserCommand>();
        myParser = new Parser(this);

        myUserCommands = new ArrayList<>();
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
    
    public Map<String, Double> getVariableMap() {
        return variableMap;
    }
        
    public void addUserCommand(UserCommand cmd){
    	myUserCommands.add(cmd);
    }
    
    public BooleanProperty clearScreenProperty(){
    	return clearScreen;
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		UserCommand outputCmd;
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
