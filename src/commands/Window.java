package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class Window extends CommandNode {

    public Window(Model myModel) {
        super(myModel, Double.class, Double.class);
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
    	getModel().wrapProperty().setValue(false);
        return putObjectInList(2);
    }

}