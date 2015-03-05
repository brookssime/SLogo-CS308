package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class Home extends CommandNode {

    public Home(Model myModel) {
        super(myModel, 0);
    }
    
    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList(myModel.getActiveTurtle().setLocation(0, 0));
    }

}
