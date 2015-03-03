package commands;

import java.util.List;

import application.Model;
import application.Turtle;

public class Home extends Command {

    public Home(Model myModel) {
        super(myModel, 0);
    }
    
    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList(myModel.getActiveTurtle().setLocation(0, 0));
    }

}
