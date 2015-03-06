package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class Cosine extends CommandNode {

    public Cosine(Model myModel) {
        super(myModel, double.class);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList(Math.cos(Math.toRadians((double) args.get(0))));
    }

}
