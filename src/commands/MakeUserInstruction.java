package commands;

import java.util.ArrayList;
import java.util.List;

import application.CommandNode;
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
        List<String> stringList = createListOfVariableNames(args);
        
        List<Object> rootObjectList = new ArrayList<>();
        rootObjectList.addAll(((EvaluatorNode) args.get(2)).evaluate());
        
        List<EvaluatorNode> rootNodeList = new ArrayList<>();
        for (Object o : rootObjectList) {
            rootNodeList.add((EvaluatorNode) o);
        }
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
    
    private List<Object> evaluateUserCommandNodes(List<Object> userCommandNodeList) {
        while(userCommandNodeList.get(0) instanceof UserCommandNode) {
            userCommandNodeList = putObjectInList(((UserCommandNode) userCommandNodeList.get(0)).evaluate());
        }
    }

    private List<String> createListOfVariableNames(List<Object> args) {
        List<Object> commandNodeList = ((EvaluatorNode) args.get(1)).evaluate();
        // Handles nested brackets [[:x]]
        while(!(commandNodeList.get(0) instanceof VariableNode)) {
            commandNodeList = ((EvaluatorNode) commandNodeList.get(0)).evaluate();
        }
        List<String> stringList = new ArrayList<>();
        for (Object o : commandNodeList) {
            List<Object> tempList = new ArrayList<>();
            tempList.addAll(((EvaluatorNode) o).evaluate());
            for (Object object : tempList) {
                stringList.add((String) object);
            }
        }
        return stringList;
    }

}
