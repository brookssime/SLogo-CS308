package application;

import java.util.ArrayList;
import java.util.List;

import commands.Command;
import commands.NodesCommand;
import commands.UserCommand;

public class UserCommandNode extends EvaluatorNode {

    private UserCommand myCommand;

    public UserCommandNode(NodesCommand myCommand) {
        // Throw an error here if casting is invalid
        this.myCommand = (UserCommand) myCommand;
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
        return myCommand.process();
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
