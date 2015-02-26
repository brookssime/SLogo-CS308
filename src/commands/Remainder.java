package commands;

import java.util.List;

import application.Model;

public class Remainder extends Command {

    public Remainder(Model myModel) {
        super(myModel, 2);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putDoubleInList((double) args.get(0) % (double) args.get(1));
    }

}
