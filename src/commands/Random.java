package commands;

import java.util.List;

import application.Model;

public class Random extends Command {
    public static java.util.Random rand;;
    
    public Random(Model myModel) {
        super(myModel, 1);
        rand = new java.util.Random();
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList(rand.nextDouble()*((double)args.get(0)));
    }

}
