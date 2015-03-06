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

public class CommandHandler extends SyntaxHandler {
    private static final String commandPath = "commands.";

    public CommandHandler(Model myModel, Parser myParser,
            List<Map.Entry<String, Pattern>> myCommandPatterns) {
        super(myModel, myParser, myCommandPatterns);
    }

    @Override
    public boolean handle(String s, Iterator<String> iter,
            List<TreeNode> nodeList) throws InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, SecurityException,
            ClassNotFoundException {
        try {
            nodeList.add((CommandNode) Class.forName(
                    commandPath + PatternMatcher.checkForMatch(s, myCommandPatterns)).getDeclaredConstructors()[0]
                    .newInstance(myModel));
        } catch (ClassNotFoundException e) {
            CommandNode cmd = (CommandNode) myModel.getUserCommand(s);
            if (nodeList.size() > 0 && nodeList.get(nodeList.size() - 1) instanceof MakeUserInstruction) {
                nodeList.add(new ConstantNode(s));
            } else if (cmd != null) {
                nodeList.add(cmd);
            } else {
                throw new InvalidCommandException(s);
            }
        }
        return true;
    }

}
