package syntax;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import commands.*;
import application.ConstantNode;
import application.Node;
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
            List<Node> nodeList) throws InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, SecurityException,
            ClassNotFoundException {
        try {
            nodeList.add((CommandNode) Class.forName(
                    commandPath + PatternMatcher.checkForMatch(s, myCommandPatterns)).getDeclaredConstructors()[0]
                    .newInstance(myModel));
        } catch (ClassNotFoundException e) {
            CommandNode cmd = (CommandNode) myModel.getUserCommand(s);
            if (cmd != null) {
                nodeList.add(cmd);
            } else if (nodeList.get(nodeList.size() - 1) instanceof MakeUserInstruction) {
                nodeList.add(new ConstantNode(s));
            } else {
                //Throw an error for incorrect command name here
            }
        }
        return true;
    }

}
