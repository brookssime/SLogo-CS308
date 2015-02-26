package commands;

import java.util.List;

import application.Model;

public class ShowTurtle extends Command {

    public ShowTurtle(Model myModel) {
        super(myModel, 0);
    }
    
    @Override
    public List<Object> function(List<Object> args) {
        myModel.getActiveTurtle().setShowing(true);
        return putDoubleInList(1);
    }

}
