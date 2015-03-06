package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import application.Model;

public class EvaluatorNode extends CommandNode {
    private List<Node> nodeList;

    public EvaluatorNode(Model myModel, List<Node> nodeList) {
        super(myModel, new Class[0]);
        this.nodeList = nodeList;
        int sum = 0;
        for (Node node : nodeList) {
            sum += node.countVariables();
        }
        setArgNum(sum);
        generateParameterArray();
    }

    @Override
    protected List<Object> function(List<Object> args) {
        List<Object> list = new ArrayList<>();
        nodeList.stream().forEach(node -> list.add(node.evaluate(args)));
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
