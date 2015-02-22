package commands;

import java.util.Iterator;

public abstract class Command {
    private int numberOfParameters;

    /**
     * @param parameters
     *            collection of parameters needed to compute the given command
     * @return double result from executing the command
     */
    public abstract double process(Turtle turtle, List<Object> parameters);

    
    public boolean receivedEnoughParameters(List<Object> parameters) {
        return parameters.size() == numberOfParameters;
    }
}
