package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class Minus extends CommandNode {

    public Minus(Model myModel) {
        super(myModel, double.class);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList((double) args.get(0) * -1);
    }

}
