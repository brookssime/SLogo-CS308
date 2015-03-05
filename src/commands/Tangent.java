package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class Tangent extends CommandNode {

    public Tangent(Model myModel) {
        super(myModel, 1);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList(Math.tan(Math.toRadians((double) args.get(0))));
    }

}
