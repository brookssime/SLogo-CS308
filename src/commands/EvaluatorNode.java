package commands;

import java.util.ArrayList;
import java.util.List;

import application.Model;
import application.Node;

public class EvaluatorNode extends CommandNode {
    private List<Node> nodeList;

    public EvaluatorNode(Model myModel, List<Node> nodeList) {
        super(myModel, 0);
        this.nodeList = nodeList;
        int sum = 0;
        for (Node node : nodeList) {
            sum += node.countVariables();
        }
        setArgNum(sum);
    }

    @Override
    protected List<Object> function(List<Object> args) {
        List<Object> list = new ArrayList<>();
        nodeList.stream().forEach(node -> list.add(node.evaluate(args)));
        return list;
    }

}
