package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import application.Model;
import application.Turtle;

public class EvaluatorNode extends CommandNode {
    private List<TreeNode> nodeList;

    public EvaluatorNode(Model myModel, List<TreeNode> nodeList) {
        super(myModel, new Class[0]);
        this.nodeList = nodeList;
        int sum = 0;
        for (TreeNode node : nodeList) {
            sum += node.countVariables();
        }
        setArgNum(sum);
        generateParameterArray();
    }

    @Override
    protected List<Object> function(Turtle myTurtle, List<Object> args) {
        List<Object> list = new ArrayList<>();
        nodeList.stream().forEach(node -> list.add(node.evaluate(myTurtle, args)));
        return list;
    }
    
    private void generateParameterArray() {
        Class[] parameterArray = new Class[getArgNum()];
        for (int i = 0; i < parameterArray.length; i++) {
            parameterArray[i] = double.class;
        }
        setParameterArray(parameterArray);
    }

}
