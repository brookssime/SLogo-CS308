package commands;

import java.util.ArrayList;
import java.util.List;

import application.Model;

public abstract class Command {

	protected Model myModel;
	protected int myArgNum;
	
	public Command(Model myModel, int myArgNum){
		this.myModel = myModel;
		this.myArgNum = myArgNum;
	}
	
    /**
     * @param parameters
     *            collection of parameters needed to compute the given command
     * @return double result from executing the command
     */
    public List<Object> process(List<Object> args){
    	//throw arg number Exceptions
    	return function(args);
    }
    
    public abstract List<Object> function(List<Object> args);
    
    protected List<Object> putDoubleInList(double a) {
        List<Object> list = new ArrayList<>();
        list.add(a);
        return list;
    }
    
    public int getArgNum() {
        return myArgNum;
    }

}
