package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.NonexistantTurtleException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class TurtleList {
	
	private List<Turtle> myActiveList = new ArrayList<Turtle>();
	private Map<Turtle, Double> IDMap = new HashMap<Turtle, Double>();
	private Map<Double, Turtle> TurtleMap = new HashMap<Double, Turtle>();
	private DoubleProperty newID = new SimpleDoubleProperty();
	
	public TurtleList(){
		addTurtle(0);
	}
	
	public List<Turtle> getActiveTurtles(){
		return myActiveList;
	}
	
	public double getID(Turtle t){
		return IDMap.get(t);
	}
	
	public Turtle getTurtle(double id){
		return TurtleMap.get(id);
	}
	
	public List<Turtle> getTurtles(List<Double> turtleIDList){
	    List<Turtle> turtleList = new ArrayList<>();
	    turtleIDList.stream().forEach(id -> addTurtleToList(id, turtleList));
		return turtleList;
	}
	
	private void addTurtleToList(double id, List<Turtle> turtleList) {
	    Turtle t = TurtleMap.get(id);
	    if (t == null) {
	        throw new NonexistantTurtleException(id);
	    }
	    turtleList.add(t);
	}
	
	public DoubleProperty IDProperty(){
		return newID;
	}
	
	public void addTurtle(double id){
		if (!TurtleMap.containsKey(id)){
			Turtle t = new Turtle();
			IDMap.put(t, id);
			TurtleMap.put(id, t);
			newID.set(id);
		}
		myActiveList.add(TurtleMap.get(id));
	}
	
	public void setActiveTurtles(List<Double> list){
		myActiveList = new ArrayList();
		for (double id: list){
		addTurtle(id);
		}
	}

	public double getTurtleCount(){
		return TurtleMap.size();
	}
}
