package commands;

import java.util.List;

import application.Model;

public class Sine extends Command {

    public Sine(Model myModel) {
        super(myModel, 1);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putDoubleInList(Math.sin(((double) args.get(0))*Math.PI/180));
    }

}
