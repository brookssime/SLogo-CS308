package commands;

import java.util.ArrayList;
import java.util.List;

import tree.CommandNode;
import tree.EvaluatorNode;
import tree.Node;
import tree.VariableNode;
import application.Model;

public class MakeUserInstruction extends CommandNode {

    public MakeUserInstruction(Model myModel) {
        super(myModel, 3);
    }

    @Override
    public List<Object> function(List<Object> args) {
        String commandName = (String) args.get(0);
        List<String> stringList = getVariableList(getRootNodes(args.get(1)));
        
        List<Node> rootNodeList = getRootNodes(args.get(2));
        
        for (Node n : rootNodeList) {
            for (int i = 0; i < stringList.size(); i ++) {  
                List<VariableNode> varNodeList = n.getVariableNodes();
                while(varNodeList.remove(null));
                for (int j = 0; j < varNodeList.size(); j++) {
                    VariableNode v = varNodeList.get(j);
                    if (((String) v.evaluate().get(0)).compareTo(stringList.get(i)) == 0) {
                        v.setIndex(i);
                    }
                }
            }
        }
        myModel.addUserCommand(commandName, new EvaluatorNode(myModel, rootNodeList));
        return putObjectInList(1);
    }

    private List<String> getVariableList(List<Node> nodeList) {
        List<String> stringList = new ArrayList<>();
        for (Node node : nodeList) {
            List<Object> tempList = new ArrayList<>();
            tempList.addAll(node.evaluate());
            for (Object object : tempList) {
                //Check for existing variables here before adding to stringList
                stringList.add((String) object);
            }
        }
        return stringList;
    }

}
