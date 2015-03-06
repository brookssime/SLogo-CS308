package commands;

import java.util.ArrayList;
import java.util.List;

import tree.BlockNode;
import tree.EvaluatorNode;
import tree.TreeNode;
import application.Model;
import tree.CommandNode;

public class DoTimes extends CommandNode {
    public DoTimes(Model myModel) {
        super(myModel, BlockNode.class, BlockNode.class);
    }

    @Override
    public List<Object> function(List<Object> args) {
        List<Object> firstBlock = new ArrayList<>();
        getRootNodes(args.get(0)).stream().forEach(node -> firstBlock.add(node.evaluate()));
        if (!checkParameters(firstBlock, String.class, double.class)) {
            //Throw incorrect parameters exception
        }
        return runForLoop(args, (int) firstBlock.get(0), (String) firstBlock.get(1));
    }

    protected List<Object> runForLoop(List<Object> args, int limit, String s) {
        List<TreeNode> nodeList = getRootNodes(args.get(1));
        EvaluatorNode evalNode = new EvaluatorNode(myModel, nodeList);
        Object result = null;;
        for (int i = 1; i <= (int) limit; i++) {
            myModel.setVariableValue(s, (double) i);
            List<Object> tempList =  evalNode.evaluate();
            result = tempList.get(tempList.size() - 1);
        }
        return putObjectInList(result);
    }
}
