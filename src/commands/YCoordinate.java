package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class YCoordinate extends CommandNode {

    public YCoordinate(Model myModel) {
        super(myModel, new Class[0]);
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        return putObjectInList(myTurtle.getY());
    }

}
