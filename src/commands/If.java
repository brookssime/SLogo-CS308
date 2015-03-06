package commands;

import java.util.List;

import application.Model;
import tree.BlockNode;
import tree.CommandNode;
import tree.EvaluatorNode;
import tree.TreeNode;

public class If extends CommandNode {

    public If(Model myModel) {
        super(myModel, Double.class, BlockNode.class);
    }

    @Override
    protected List<Object> function(List<Object> args) {
        Object result = (double) 0;
        if ((double) args.get(0) != (double) 0) {
            result = evaluateCommand(args.get(1));
        }
        return putObjectInList(result);
    }

    protected Object evaluateCommand(Object o) {
        Object result;
        List<TreeNode> nodeList = getRootNodes(o);
        EvaluatorNode evalNode = new EvaluatorNode(myModel, nodeList);
        List<Object> tempList =  evalNode.evaluate();
        result = tempList.get(tempList.size() - 1);
        return result;
    }

}
