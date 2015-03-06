package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class And extends CommandNode {
    
    public And(Model myModel) {
        super(myModel, Double.class, Double.class);
    }

    @Override
    public List<Object> function(List<Object> args) {
        if ((double) args.get(0) != (double) 0 && (double) args.get(1) != (double) 0) {
            return putObjectInList((double) 1);
        }
        return putObjectInList((double) 0);
    }

}
