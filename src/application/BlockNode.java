package application;

import java.util.ArrayList;
import java.util.List;

public class BlockNode extends Node {

    private List<Node> nodeList;

    public BlockNode(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    /**
     * Returns a list of CommandNodes if they are the direct result of this
     * UserCommandNode's command
     * 
     * Otherwise this method returns the UserCommandNode whose evaluation will
     * return CommandNodes
     */
    @Override
    public List<Object> evaluate(List<Object> args) {
        List<Object> list = new ArrayList<>();
        nodeList.stream().forEach(node -> list.add((Object) node));
        return list;
    }

    @Override
    public int countVariables() {
        return 0;
    }

    @Override
    public List<VariableNode> getVariableNodes() {
        List<VariableNode> variableList = new ArrayList<>();
        return variableList;
    }

}
