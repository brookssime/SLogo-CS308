package commands;

import java.util.List;

import application.Model;

public class ArcTangent extends Command {

    public ArcTangent(Model myModel) {
        super(myModel, 1);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putDoubleInList(Math.atan((double) args.get(0))*180/Math.PI);
    }

}
