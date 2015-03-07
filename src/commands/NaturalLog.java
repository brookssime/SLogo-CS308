package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;
import application.Turtle;

public class NaturalLog extends CommandNode {

    public NaturalLog(Model myModel) {
        super(null, Double.class);
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        return putObjectInList(Math.log((double) args.get(0)));
    }

}
