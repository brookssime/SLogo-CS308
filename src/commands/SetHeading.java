package commands;

import java.util.List;

public class SetHeading extends Command {
    private static double FULL_ROTATION = 360;

    @Override
    public List<Object> function(List<Object> args) {
        double newHeading = ((double) args.get(0)) % FULL_ROTATION;
        double oldHeading = myModel.getActiveTurtle().getHeading();
        myModel.getActiveTurtle().setHeading(newHeading);
        return putDoubleInList(newHeading - oldHeading);
    }

}
