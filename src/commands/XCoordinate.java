package commands;

import java.util.List;

import application.Model;

public class XCoordinate extends CommandNode {

    public XCoordinate(Model myModel) {
        super(myModel, 0);
    }

    @Override
    public List<Object> function(List<Object> args) {
        return putObjectInList(myModel.getActiveTurtle().getX());
    }

}
