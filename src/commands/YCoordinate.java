package commands;

import java.util.List;

import application.Model;

public class YCoordinate extends Command {

    public YCoordinate(Model myModel) {
        super(myModel, 0);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putDoubleInList(myModel.getActiveTurtle().getY());
    }

}
