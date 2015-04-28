package commands;

import java.util.List;
import application.Model;
import application.Turtle;
import tree.CommandNode;


public class Wrap extends CommandNode {

    public Wrap (Model myModel) {
        super(myModel, new Class[0]);
    }
    
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        getModel().setWindowType("Wrap");
        return putObjectInList((double) 1);
    }

}
