package commands;

import java.util.List;

import application.Model;
import application.Turtle;
import tree.BlockNode;

public class Repeat extends DoTimes {

    public Repeat(Model myModel) {
        super(myModel);
        setParameterArray(Double.class, BlockNode.class);
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        return runForLoop(args, ((Double) args.get(0)).intValue(), ":repcount");
    }

}
