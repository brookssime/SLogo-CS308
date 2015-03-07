package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class SetPosition extends CommandNode {

    public SetPosition(Model myModel) {
        super(myModel, Double.class, Double.class);
    }

    // Need to add error checking for valid x and y values
    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        return putObjectInList(myTurtle.setLocation(
                (double) args.get(0), (double) args.get(1)));
    }

}
