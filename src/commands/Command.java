package commands;

import java.util.List;

import application.Model;

public abstract class Command {

	protected int myArgNum;
	
	public Command(int argNum){
		myArgNum = argNum;
	}
	
    /**
     * @param parameters
     *            collection of parameters needed to compute the given command
     * @return double result from executing the command
     */
    public double process(Model model, List<Object> parameters){
    	//throw arg number Exceptions
    	return function(parameters);
    }
    
    public abstract double function(List<Object> parameters);

}
