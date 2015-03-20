package syntax;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import tree.CommandNode;
import tree.ConstantNode;
import tree.TreeNode;
import commands.*;
import exceptions.InvalidCommandException;
import application.Model;
import application.Parser;
import application.PatternMatcher;

/**
 * Appropriately handles any action required for a token of the command syntax
 * type
 * 
 * @author Tom
 *
 */
public class CommandHandler extends SyntaxHandler {
    private static final String COMMAND_PATH = "commands.";

    public CommandHandler(Model myModel, Parser myParser,
            List<Map.Entry<String, Pattern>> myCommandPatterns) {
        super(myModel, myParser, myCommandPatterns);
    }

    /**
     * Tries to add a CommandNode to the nodeList. If the command does not
     * exist, and the previous node was the MakeUserInstruction command, then a
     * ConstantNode is added to the nodeList. Otherwise, an invalid command
     * exception is thrown.
     */
    @Override
    public boolean handle(String token, Iterator<String> iter,
            List<TreeNode> nodeList) throws InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, SecurityException,
            ClassNotFoundException {
        try {
            nodeList.add((CommandNode) Class.forName(
                    COMMAND_PATH + PatternMatcher.checkForMatch(token, getCommandPatterns())).getDeclaredConstructors()[0]
                    .newInstance(getModel()));
        } catch (ClassNotFoundException e) {
            CommandNode cmd = (CommandNode) getModel().getUserCommand(token);
            if (nodeList.size() > 0 && nodeList.get(nodeList.size() - 1) instanceof MakeUserInstruction) {
                nodeList.add(new ConstantNode(token));
            } else if (cmd != null) {
                nodeList.add(cmd);
            } else {
                throw new InvalidCommandException(token);
            }
        }
        return true;
    }

}
