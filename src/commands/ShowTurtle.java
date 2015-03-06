package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class ShowTurtle extends CommandNode {

    public ShowTurtle(Model myModel) {
        super(myModel, new Class[0]);
    }
    
    @Override
    public List<Object> function(List<Object> args) {
        getModel().getActiveTurtle().setShowing(true);
        return putObjectInList((double) 1);
    }

}
