package commands;

import java.util.Iterator;

public abstract class Command {

    /**
     * @param parameters
     *            collection of parameters needed to compute the given command
     * @return double result from executing the command
     */
    public abstract double process(Iterator<Object> parameters);

}
