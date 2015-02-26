package commands;

import java.util.ArrayList;
import java.util.List;

import application.EvaluatorNode;
import application.Model;
import application.VariableNode;

public class MakeUserInstruction extends Command {

    public MakeUserInstruction(Model myModel) {
        super(myModel, 3);
    }

    @Override
    public List<Object> function(List<Object> args) {
        String commandName = (String) args.get(0);
        List<Object> nodeList = ((EvaluatorNode) args.get(1)).evaluate();
        List<Object> varObjectList = new ArrayList<>();
        for (Object o : nodeList) {
            varObjectList.addAll(((EvaluatorNode) o).evaluate());
        }
        List<String> varList = new ArrayList<>();
        for (Object o : varObjectList) {
            varList.add((String) o);
        }
        List<Object> rootObjectList = new ArrayList<>();
        rootObjectList.addAll(((EvaluatorNode) args.get(2)).evaluate());
        List<EvaluatorNode> rootNodeList = new ArrayList<>();
        for (Object o : rootObjectList) {
            rootNodeList.add((EvaluatorNode) o);
        }
        for (EvaluatorNode n : rootNodeList) {
            for (int i = 0; i < varList.size(); i ++) {  
                List<VariableNode> varNodeList = n.getVariableNodes();
                while(varNodeList.remove(null));
                for (int j = 0; j < varNodeList.size(); j++) {
                    VariableNode v = varNodeList.get(j);
                    if (((String) v.evaluate().get(0)).compareTo(varList.get(i)) == 0) {
                        v.setIndex(i);
                    }
                }
            }
        }
        myModel.addUserCommand(commandName, new EvaluatorCommand(myModel, rootNodeList));
        return putDoubleInList(1);
    }

}
