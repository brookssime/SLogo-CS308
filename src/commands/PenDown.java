package commands;

import java.util.List;

import application.Model;

public class PenDown extends Command {

    public PenDown(Model myModel) {
        super(myModel, 0);
    }
    
    @Override
    public List<Object> function(List<Object> args) {
        myModel.getActiveTurtle().setPenDown(true);
        return putDoubleInList(1);
    }

}
