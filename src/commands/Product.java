package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class Product extends CommandNode {

    public Product(Model myModel) {
        super(myModel, Double.class, Double.class);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList((double) args.get(0) * (double) args.get(1));
    }

}
