package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class HideTurtle extends CommandNode {

    public HideTurtle(Model myModel) {
        super(myModel, new Class[0]);
    }
    
    @Override
    public List<Object> function(List<Object> args) {
        myModel.getActiveTurtle().setShowing(false);
        return putObjectInList((double) 0);
    }

}
