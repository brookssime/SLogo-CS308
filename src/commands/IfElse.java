package commands;

import java.util.List;

import tree.BlockNode;
import application.Model;
import application.Turtle;

public class IfElse extends If {

    public IfElse(Model myModel) {
        super(myModel);
        setParameterArray(Double.class, BlockNode.class, BlockNode.class);
    }
    
    @Override
    protected List<Object> function(Turtle myTurtle, List<Object> args) {
        Object result = (double) 0;
        if ((double) args.get(0) != (double) 0) {
            result = evaluateCommand(args.get(1));
        } else {
            result = evaluateCommand(args.get(2));
        }
        return putObjectInList(result);
    }

}
