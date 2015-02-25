package commands;

import java.util.List;

import application.Model;

public class IsShowing extends Command {

    public IsShowing(Model myModel) {
        super(myModel, 0);
    }

    @Override
    public List<Object> function(List<Object> args) {
        if (myModel.getActiveTurtle().isShowing()) {
            return putDoubleInList(1);
        }
        return putDoubleInList(0);
    }

}
