package commands;

import java.util.List;

import tree.BlockNode;
import application.Model;

public class IfElse extends If {

    public IfElse(Model myModel) {
        super(myModel);
        setParameterArray(Double.class, BlockNode.class, BlockNode.class);
    }
    
    @Override
    protected List<Object> function(List<Object> args) {
        Object result = (double) 0;
        if ((double) args.get(0) != (double) 0) {
            result = evaluateCommand(args.get(1));
        } else {
            result = evaluateCommand(args.get(2));
        }
        return putObjectInList(result);
    }

}
