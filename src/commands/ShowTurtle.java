package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class ShowTurtle extends CommandNode {

    public ShowTurtle(Model myModel) {
        super(myModel, new Class[0]);
    }
    
    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        myTurtle.setShowing(true);
        return putObjectInList((double) 1);
    }

}
