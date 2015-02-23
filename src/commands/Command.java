package commands;

import java.util.List;

import application.Model;

public abstract class Command {
    private int numberOfParameters;

	protected int myArgNum;
	
	public Command(int argNum){
		myArgNum = argNum;
	}
	
    /**
     * @param parameters
     *            collection of parameters needed to compute the given command
     * @param model
     *   
     * @return double result from executing the command
     */
    public double process(Model model, List<Object> parameters){
    	//throw arg number Exceptions
    	return function(model, parameters);
    }
    
    public abstract double function(Model model, List<Object> parameters);
}
