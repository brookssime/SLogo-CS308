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
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Model implements Observer {
    private Parser myParser;
    private TurtleList myTurtleList;
    private Map<String, CommandNode> myUserCommands;
    private Map<String, CommandNode> myCommandHistory;
    private Map<String, Double> variableMap;
    private double maxX;
    private double maxY;
    private String myLanguage;
    private StringProperty errorMsg = new SimpleStringProperty();
    private DoubleProperty background = new SimpleDoubleProperty();
    private DoubleProperty penSize = new SimpleDoubleProperty();
    private DoubleProperty penColor = new SimpleDoubleProperty();
    private ObjectProperty<double[]> pallet = new SimpleObjectProperty<>();
    private BooleanProperty clearScreen = new SimpleBooleanProperty();
    private BooleanProperty clearStamp = new SimpleBooleanProperty();
    private String windowType;

    public Model (double maxX, double maxY) {
        myLanguage = "English";
        myParser = new Parser(this);
        myTurtleList = new TurtleList();
        myUserCommands = new HashMap<String, CommandNode>();
        myCommandHistory = new HashMap<String, CommandNode>();
        variableMap = new HashMap<String, Double>();
        this.maxX = maxX;
        this.maxY = maxY;
        windowType = "Wrap";
    }

    public void updateCommandPatterns () {
        myParser.updateCommandPatterns();
    }

    public TurtleList getTurtleList () {
        return myTurtleList;
    }

    public void setLanguage (String language) {
        myLanguage = language;
        // this.language.bind(view.);
    }

    public String getLanguage () {
        return myLanguage;
    }

    public double getMaxX () {
        return maxX;
    }

    public double getMaxY () {
        return maxY;
    }

    public Double getVariableValue (String key) {
        return variableMap.get(key);
    }

    public void setVariableValue (String key, double value) {
        variableMap.put(key, value);
    }

    public void removeVariableValue (String key) {
        variableMap.remove(key);
    }

    public void addUserCommand (String key, EvaluatorNode value) {
        myUserCommands.put(key, value);
    }

    public CommandNode getUserCommand (String key) {
        return myUserCommands.get(key);
    }

    public StringProperty errorMessageProperty () {
        return errorMsg;
    }

    public DoubleProperty backgroundProperty () {
        return background;
    }

    public DoubleProperty penSizeProperty () {
        return penSize;
    }

    public DoubleProperty penColorProperty () {
        return penColor;
    }

    public ObjectProperty<double[]> palletProperty () {
        return pallet;
    }

    public BooleanProperty clearScreenProperty () {
        return clearScreen;
    }

    public BooleanProperty clearStampProperty () {
        return clearStamp;
    }

    @Override
    public void update (Observable arg0, Object arg1) {
        CommandNode outputCmd;
        try {
            outputCmd = myParser.parse(arg1.toString());
            myCommandHistory.put(arg1.toString(), outputCmd);
            outputCmd.evaluate();
        }
        catch (InstantiationException | IllegalAccessException
                | InvocationTargetException | ClassNotFoundException | UnbalancedBracketsException
                | InvalidCommandException | IncorrectParametersException
                | NonexistantTurtleException | InvalidWindowBehaviorException e) {
            errorMsg.set(e.getMessage());
        }
    }
    
    public String getWindowType () {
        return windowType;
    }
    
    public void setWindowType (String type) {
        windowType = type;
    }

}
