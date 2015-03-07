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
        List<Object> turtleIDList= new ArrayList<>();
        getRootNodes(args.get(0)).stream().forEach(node -> turtleIDList.add(node.evaluate()));
        checkParameters(turtleIDList, generateClassArray(Double.class, turtleIDList.size()));
        List<Double> doubleList = new ArrayList<>();
        turtleIDList.stream().forEach(o -> doubleList.add((Double) o));
        getModel().getTurtleList().setActiveTurtles(doubleList);
        return putObjectInList(turtleIDList.get(turtleIDList.size() - 1));        
    }

}
