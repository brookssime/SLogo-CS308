package commands;

import java.util.List;

import application.Model;

public class Minus extends Command {

    public Minus(Model myModel) {
        super(myModel, 1);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList((double) args.get(0) * -1);
    }

}
