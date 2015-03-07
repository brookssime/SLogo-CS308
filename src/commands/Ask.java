package commands;

import java.util.ArrayList;
import java.util.List;

import application.Model;
import application.Turtle;
import tree.BlockNode;
import tree.CommandNode;
import tree.EvaluatorNode;

public class Ask extends CommandNode {

    public Ask(Model myModel) {
        super(myModel, BlockNode.class, BlockNode.class);
    }

    @Override
    protected List<Object> function(Turtle myTurtle, List<Object> args) {
        List<Double> doubleList = blockToDoubleList((BlockNode) args.get(0));
        List<Turtle> turtleList = getModel().getTurtleList().getTurtles(doubleList);
        EvaluatorNode myEvaluatorNode = new EvaluatorNode(getModel(), getRootNodes(args.get(1)));
        List<Object> result = new ArrayList<>();
        for (Turtle t : turtleList) {
            result = myEvaluatorNode.evaluate(t, null);
        }
        return result;
    }

}
