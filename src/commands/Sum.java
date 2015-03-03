package commands;

import java.util.List;

import application.Model;

public class Sum extends Command {

    public Sum(Model myModel) {
        super(myModel, 2);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList((double) args.get(0) + (double) args.get(1));
    }

}
