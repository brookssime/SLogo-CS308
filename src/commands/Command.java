package commands;

import java.util.List;

import application.Model;

public abstract class Command {

	protected Model myModel;
	protected int myArgNum;
	
	public Command(Model model, int argNum){
		myModel = model;
		myArgNum = argNum;
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

}
