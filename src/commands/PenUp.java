package commands;

import java.util.List;

import application.Model;

public class PenUp extends Command {

    public PenUp(Model myModel) {
        super(myModel, 0);
    }
    
    @Override
    public List<Object> function(List<Object> args) {
        myModel.getActiveTurtle().setPenDown(false);
        return putDoubleInList(0);
    }

}
