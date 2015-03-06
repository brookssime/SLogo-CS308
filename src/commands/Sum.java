package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class Sum extends CommandNode {

    public Sum(Model myModel) {
        super(myModel, Double.class, Double.class);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList((double) args.get(0) + (double) args.get(1));
    }

}
