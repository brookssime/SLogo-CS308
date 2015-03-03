package commands;

import java.util.List;

import application.Model;

public class Cosine extends Command {

    public Cosine(Model myModel) {
        super(myModel, 1);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList(Math.cos(((double) args.get(0))*Math.PI/180));
    }

}
