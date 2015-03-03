package commands;

import java.util.List;

import application.Model;

public class NaturalLog extends Command {

    public NaturalLog(Model myModel) {
        super(myModel, 1);
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList(Math.log((double) args.get(0)));
    }

}
