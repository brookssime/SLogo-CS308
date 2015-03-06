package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class ArcTangent extends CommandNode {

    public ArcTangent(Model myModel) {
        super(myModel, double.class);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList(Math.toDegrees(Math.atan((double) args.get(0))));
    }

}
