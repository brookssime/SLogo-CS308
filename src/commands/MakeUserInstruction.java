package commands;

import java.util.ArrayList;
import java.util.List;

import exceptions.IncorrectParametersException;
import tree.BlockNode;
import tree.CommandNode;
import tree.EvaluatorNode;
import tree.TreeNode;
import tree.VariableNode;
import application.Model;
import application.Turtle;

public class MakeUserInstruction extends CommandNode {

    public MakeUserInstruction(Model myModel) {
        super(myModel, String.class, BlockNode.class, BlockNode.class);
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        String commandName = (String) args.get(0);
        List<String> stringList = getVariableList(getRootNodes(args.get(1)));
        List<TreeNode> rootNodeList = getRootNodes(args.get(2));
        rootNodeList.stream().forEach(n -> setVariableNodeIndices(stringList, n));
        getModel().addUserCommand(commandName, new EvaluatorNode(getModel(), rootNodeList));
        return putObjectInList(1);
    }

    private void setVariableNodeIndices(List<String> stringList, TreeNode node) {
        for (int i = 0; i < stringList.size(); i++) {
            List<VariableNode> varNodeList = node.getVariableNodes();
            for (int j = 0; j < varNodeList.size(); j++) {
                VariableNode varNode = varNodeList.get(j);
                if (((String) varNode.evaluate().get(0)).compareTo(stringList.get(i)) == 0) {
                    varNode.setIndex(i);
                }
            }
        }
    }

    private List<String> getVariableList(List<TreeNode> nodeList) {
        List<String> stringList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        nodeList.stream().forEach(node -> objectList.addAll(node.evaluate()));
        checkParameters(objectList, generateClassArray(String.class, objectList.size()));
        objectList.stream().forEach(o -> stringList.add((String) o));
        return stringList;
    }

}
