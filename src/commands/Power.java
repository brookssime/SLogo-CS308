package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class Power extends CommandNode {

    public Power(Model myModel) {
        super(myModel, double.class, double.class);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList(Math.pow((double) args.get(0), (double) args.get(1)));
    }

}
