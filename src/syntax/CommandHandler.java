package syntax;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import commands.Command;
import application.CommandNode;
import application.ConstantNode;
import application.EvaluatorNode;
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
            List<EvaluatorNode> nodeList) throws InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, SecurityException,
            ClassNotFoundException {
        String p = PatternMatcher.checkForMatch(s, myCommandPatterns);
        if (p != null) {
            nodeList.add(new CommandNode((Command) Class.forName(
                    commandPath + p).getDeclaredConstructors()[0]
                    .newInstance(myModel)));
        } else {
            Command cmd = (Command) myModel.getUserCommand(s);
            if (cmd != null) {
                nodeList.add(new CommandNode(cmd));
            } else {
                nodeList.add(new ConstantNode(s));
            }
        }
        return true;
    }

}
