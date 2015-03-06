package commands;

import java.util.List;

import tree.CommandNode;
import application.Model;

public class Pi extends CommandNode {

    public Pi(Model myModel) {
        super(myModel, new Class[0]);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList(Math.PI);
    }

}
