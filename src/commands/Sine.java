package commands;

import java.util.List;

import application.Model;

public class Sine extends CommandNode {

    public Sine(Model myModel) {
        super(myModel, 1);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList(Math.sin(Math.toRadians((double) args.get(0))));
    }

}
