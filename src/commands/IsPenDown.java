package commands;

import java.util.List;

import application.Model;

public class IsPenDown extends Command {

    public IsPenDown(Model myModel) {
        super(myModel, 0);
    }

    @Override
    public List<Object> function(List<Object> args) {
        if (myModel.getActiveTurtle().isPenDown()) {
            return putDoubleInList(1);
        }
        return putDoubleInList(0);
    }

}
