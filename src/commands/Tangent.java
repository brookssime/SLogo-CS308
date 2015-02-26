package commands;

import java.util.List;

import application.Model;

public class Tangent extends Command {

    public Tangent(Model myModel) {
        super(myModel, 1);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putDoubleInList(Math.tan(((double) args.get(0))*Math.PI/180));
    }

}
