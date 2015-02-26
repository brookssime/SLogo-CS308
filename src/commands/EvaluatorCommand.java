package commands;

import java.util.ArrayList;
import java.util.List;

import application.Model;
import application.EvaluatorNode;

public class EvaluatorCommand extends NodesCommand {
    
    public EvaluatorCommand(Model model, List<EvaluatorNode> nodeList) {
        super(model, nodeList);
    }

    @Override
    public List<Object> function(List<Object> args) {
        List<Object> output = new ArrayList<>();
        for (EvaluatorNode node: getNodes()){
            output.addAll(node.evaluate(args));
        }
        return output;
    }
    
}
