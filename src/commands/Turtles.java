package commands;

import java.util.List;

import application.Model;
import application.Turtle;
import tree.CommandNode;

public class Turtles extends CommandNode {

    public Turtles(Model myModel) {
        super(myModel, new Class[0]);
    }

    @Override
    protected List<Object> function(Turtle myTurtle, List<Object> args) {
        return putObjectInList(getModel().getTurtleGroup().getTurtleCount());
    }

}
