package commands;

import java.util.List;

import application.Model;

public class Power extends Command {

    public Power(Model myModel) {
        super(myModel, 2);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putDoubleInList(Math.pow((double) args.get(0), (double) args.get(1)));
    }

}
