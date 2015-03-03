package commands;

import java.util.List;

import application.Model;
import application.Turtle;

public class SetPosition extends Command {

    public SetPosition(Model myModel) {
        super(myModel, 2);
    }

    // Need to add error checking for valid x and y values
    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList(myModel.getActiveTurtle().setLocation(
                (double) args.get(0), (double) args.get(1)));
    }

}
