package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class MakeVariable extends CommandNode {

    public MakeVariable(Model myModel) {
        super(myModel, 1);
    }

    @Override
    public List<Object> function(List<Object> args) {
        myModel.setVariableValue((String) args.get(0), (double) args.get(1));
        return putObjectInList((double) args.get(1));
    }

}
