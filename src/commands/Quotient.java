package commands;

import java.util.List;

import application.Model;

public class Quotient extends Command {

    public Quotient(Model myModel) {
        super(myModel, 2);
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putDoubleInList((double) args.get(0) / (double) args.get(1));
    }

}