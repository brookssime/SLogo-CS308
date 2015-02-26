package commands;

import java.util.List;

import application.EvaluatorNode;
import application.Model;

public abstract class NodesCommand extends Command {
    private List<EvaluatorNode> myNodes;
    
    public NodesCommand(Model model, List<EvaluatorNode> nodeList){
        super(model, countVariables(nodeList));
        myNodes = nodeList;
    }
    
    public NodesCommand(Model model, int i) {
        super(model, i);
    }

    protected static int countVariables(List<EvaluatorNode> nodeList) {
        int sum = 0;
        for (EvaluatorNode node : nodeList){
            sum += node.countVariables();
        }
        return sum;
    }
    
    protected List<EvaluatorNode> getNodes() {
        return myNodes;
    }

    @Override
    public abstract List<Object> function(List<Object> args);
}
