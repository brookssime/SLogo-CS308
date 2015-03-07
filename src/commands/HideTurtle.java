package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class HideTurtle extends CommandNode {

    public HideTurtle(Model myModel) {
        super(null, new Class[0]);
    }
    
    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        myTurtle.setShowing(false);
        return putObjectInList((double) 0);
    }

}
