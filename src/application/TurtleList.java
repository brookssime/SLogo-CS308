package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurtleList {
	
	private List<Turtle> myActiveList = new ArrayList<Turtle>();
	private Map<Turtle, Double> IDMap = new HashMap<Turtle, Double>();
	private Map<Double, Turtle> TurtleMap = new HashMap<Double, Turtle>();
	
	public List<Turtle> getActiveTurtles(){
		return myActiveList;
	}
	
	public double getID(Turtle t){
		return IDMap.get(t);
	}
	
	public Turtle getTurtle(double id){
		return TurtleMap.get(id);
	}
	
	public void addTurtle(double id){
		if (!TurtleMap.containsKey(id)){
			Turtle t = new Turtle();
			IDMap.put(t, id);
			TurtleMap.put(id, t);
		}
		myActiveList.add(TurtleMap.get(id));
	}

	public double getTurtleCount(){
		return TurtleMap.size();
	}
}
