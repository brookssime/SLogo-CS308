package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class NaturalLog extends CommandNode {

    public NaturalLog(Model myModel) {
        super(myModel, double.class);
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList(Math.log((double) args.get(0)));
    }

}
