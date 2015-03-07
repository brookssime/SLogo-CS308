package commands;

import java.util.List;

import application.Model;
import application.Turtle;
import tree.BlockNode;
import tree.CommandNode;

public class Ask extends CommandNode {

    public Ask(Model myModel) {
        super(myModel, BlockNode.class, BlockNode.class);
    }

    @Override
    protected List<Object> function(Turtle myTurtle, List<Object> args) {
        // TODO Auto-generated method stub
        return null;
    }

}
