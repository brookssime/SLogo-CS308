package commands;

import java.util.List;

import application.Model;

public class Heading extends Command {

    public Heading(Model myModel) {
        super(myModel, 0);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putDoubleInList(myModel.getActiveTurtle().getHeading());
    }

}
