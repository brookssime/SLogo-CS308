package commands;

import java.util.ArrayList;
import java.util.List;

import application.EvaluatorNode;
import application.Model;
import application.UserCommandNode;
import application.VariableNode;

public class MakeUserInstruction extends Command {

    public MakeUserInstruction(Model myModel) {
        super(myModel, 3);
    }

    @Override
    public List<Object> function(List<Object> args) {
        String commandName = (String) args.get(0);
        List<String> stringList = getVariableList(getRootNodes(args.get(1)));
        
        List<EvaluatorNode> rootNodeList = getRootNodes(args.get(2));
        
        for (EvaluatorNode n : rootNodeList) {
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
        myModel.addUserCommand(commandName, new EvaluatorCommand(myModel, rootNodeList));
        return putObjectInList(1);
    }
    
    private List<EvaluatorNode> getRootNodes(Object myUserCommandNode) {
        List<Object> rootObjectList = putObjectInList(myUserCommandNode);
        while(rootObjectList.get(0) instanceof UserCommandNode) {
            rootObjectList = ((UserCommandNode) rootObjectList.get(0)).evaluate();
        }
        List<EvaluatorNode> rootNodeList = new ArrayList<>();
        rootObjectList.stream().forEach(o -> rootNodeList.add((EvaluatorNode) o));
        return rootNodeList;
    }

    private List<String> getVariableList(List<EvaluatorNode> nodeList) {
        List<String> stringList = new ArrayList<>();
        for (EvaluatorNode node : nodeList) {
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
