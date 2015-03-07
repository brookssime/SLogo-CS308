package commands;

import java.util.ArrayList;
import java.util.List;

import exceptions.IncorrectParametersException;
import tree.BlockNode;
import tree.EvaluatorNode;
import tree.TreeNode;
import application.Model;
import application.Turtle;
import tree.CommandNode;

public class DoTimes extends CommandNode {
    public DoTimes(Model myModel) {
        super(myModel, BlockNode.class, BlockNode.class);
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        List<Object> firstBlock = new ArrayList<>();
        getRootNodes(args.get(0)).stream().forEach(node -> firstBlock.add(node.evaluate()));
        checkParameters(firstBlock, String.class, double.class);
        return runForLoop(args, ((Double) firstBlock.get(0)).intValue(), (String) firstBlock.get(1));
    }

    protected List<Object> runForLoop(List<Object> args, int end, String s) {
        return runForLoop(args, end, s, 1, 1);
    }
    
    protected List<Object> runForLoop(List<Object> args, int end, String s, int start, int increment) {
        List<TreeNode> nodeList = getRootNodes(args.get(1));
        EvaluatorNode evalNode = new EvaluatorNode(getModel(), nodeList);
        Object result = null;
        for (int i = start; i <= (int) end; i += increment) {
            getModel().setVariableValue(s, (double) i);
            List<Object> tempList =  evalNode.evaluate();
            result = tempList.get(tempList.size() - 1);
        }
        getModel().removeVariableValue(s);
        return putObjectInList(result);
    }
}
