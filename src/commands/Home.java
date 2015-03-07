package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class Home extends CommandNode {

    public Home(Model myModel) {
        super(myModel, new Class[0]);
    }
    
    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        return putObjectInList(myTurtle.setLocation(0, 0));
    }

}
