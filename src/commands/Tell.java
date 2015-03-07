package commands;

import java.util.ArrayList;
import java.util.List;

import application.Model;
import application.Turtle;
import tree.BlockNode;
import tree.CommandNode;
import tree.TreeNode;

public class Tell extends CommandNode {

    public Tell(Model myModel) {
        super(myModel, BlockNode.class);
    }

    @Override
    protected List<Object> function(Turtle myTurtle, List<Object> args) {
        List<Double> doubleList = blockToDoubleList((BlockNode) args.get(0));
        getModel().getTurtleList().setActiveTurtles(doubleList);
        return putObjectInList(doubleList.get(doubleList.size() - 1));        
    }

}
