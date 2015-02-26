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
        List<Object> varObjectList = ((EvaluatorNode) args.get(1)).evaluate();
        List<String> varList = new ArrayList<>();
        for (Object o : varObjectList) {
            varList.add((String) o);
        }
        List<Object> rootObjectList = ((EvaluatorNode) args.get(2)).evaluate();
        List<EvaluatorNode> rootNodeList = new ArrayList<>();
        for (Object o : varObjectList) {
            rootNodeList.add((EvaluatorNode) o);
        }
        //while(rootNodeList.remove(null));
        for (EvaluatorNode n : rootNodeList) {
            for (int i = 0; i < varList.size(); i ++) {  
                List<VariableNode> varNodeList = n.getVariableNodes();
                for (int j = 0; j < varNodeList.size(); j++) {
                    VariableNode v = varNodeList.get(j);
                    System.out.println(v.evaluate().get(0));
                    if (((String) v.evaluate().get(0)).equals(varList.get(i))) {
                        v.setIndex(i);
                    }
                }
            }
        }
        myModel.addUserCommand(commandName, new EvaluatorCommand(myModel, rootNodeList));
        System.out.println("Getting here?");
        return putDoubleInList(1);
    }

}
