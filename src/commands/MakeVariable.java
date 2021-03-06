package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class MakeVariable extends CommandNode {

    public MakeVariable(Model myModel) {
        super(myModel, String.class, Double.class);
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        getModel().setVariableValue((String) args.get(0), (double) args.get(1));
        return putObjectInList((double) args.get(1));
    }

}
