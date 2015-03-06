package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class Difference extends CommandNode {

    public Difference(Model myModel) {
        super(myModel, double.class, double.class);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList((double) args.get(0) - (double) args.get(1));
    }

}
