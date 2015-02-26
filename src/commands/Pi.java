package commands;

import java.util.List;

import application.Model;

public class Pi extends Command {

    public Pi(Model myModel) {
        super(myModel, 0);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putDoubleInList(Math.PI);
    }

}
