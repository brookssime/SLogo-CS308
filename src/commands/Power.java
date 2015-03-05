package commands;

import java.util.List;

import application.Model;

public class Power extends CommandNode {

    public Power(Model myModel) {
        super(myModel, 2);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList(Math.pow((double) args.get(0), (double) args.get(1)));
    }

}
