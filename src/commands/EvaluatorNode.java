package commands;

import java.util.List;

import application.Model;
import application.Node;

public class EvaluatorNode extends CommandNode {
    
    public EvaluatorNode(Model myModel, List<Node> nodeList) {
        super(myModel, 0);
        addChild((Node[]) nodeList.toArray());
    }
    
    public EvaluatorNode(Model myModel, List<Node> nodeList, int myArgNum) {
        super(myModel, myArgNum);
        addChild((Node[]) nodeList.toArray());
    }

    @Override
    protected List<Object> function(List<Object> args) {
        return args;
    }
    
}
