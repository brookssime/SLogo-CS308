package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class ArcTangent extends CommandNode {

    public ArcTangent(Model myModel) {
        super(null, Double.class);
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        return putObjectInList(Math.toDegrees(Math.atan((double) args.get(0))));
    }

}
