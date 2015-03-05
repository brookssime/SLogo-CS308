package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class HideTurtle extends CommandNode {

    public HideTurtle(Model myModel) {
        super(myModel, 0);
    }
    
    @Override
    public List<Object> function(List<Object> args) {
        myModel.getActiveTurtle().setShowing(false);
        return putObjectInList(0);
    }

}
