package commands;

import java.util.ArrayList;
import java.util.List;

import application.Model;
import application.EvaluatorNode;

public class UserCommand extends NodesCommand {

    public UserCommand(Model model, List<EvaluatorNode> nodeList) {
        super(model, nodeList);
        myArgNum = 0;
    }

    public List<Object> process(){
        return function(new ArrayList<Object>());
    }
    
    @Override
    public List<Object> function(List<Object> args) {
        List<Object> list = new ArrayList<>();
        for (EvaluatorNode n : getNodes()) {
            list.add((Object) n);
        }
        return list;
    }

}
