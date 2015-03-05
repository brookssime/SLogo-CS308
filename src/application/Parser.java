package application;

import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import commands.*;

public class Parser {
    private List<Map.Entry<String, Pattern>> myCommandPatterns;
    private List<Map.Entry<String, Pattern>> mySyntaxPatterns;
    private Model myModel;
    private static final String commandPath = "commands.";
    private TreeBuilder myTreeBuilder;
    private int blockDepthCount = 0;

    public Parser(Model myModel) {
        myCommandPatterns = PatternMatcher.makePatterns("resources/languages/English");
        mySyntaxPatterns = PatternMatcher.makePatterns("resources/languages/Syntax");
        myTreeBuilder = new TreeBuilder();
        this.myModel = myModel;
        
    }

    public EvaluatorCommand parse(String input) throws InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        return (EvaluatorCommand) parseIterator(Arrays.asList(input.split(" ")).iterator());
    }

    private NodesCommand parseIterator(Iterator<String> iter)
            throws InstantiationException, IllegalAccessException,
            InvocationTargetException, ClassNotFoundException {
        List<EvaluatorNode> nodeList = new ArrayList<>();
        while(iter.hasNext()) {
            String s = iter.next();
            String p = PatternMatcher.checkForMatch(s, mySyntaxPatterns);
            if (p == null) {
                //do nothing
            } else if (p.equals("ListEnd") || p.equals("GroupEnd")) {
                if (blockDepthCount > 0) {
                    blockDepthCount--;
                    return new UserCommand(myModel, myTreeBuilder.build(nodeList));
                }
                else {
                    //Throw an uneven bracket count error
                }
            } else if (p.equals("Comment")) {
                removeComment(iter);
            } else {
                nodeList.add(generateNode(s, p, iter));
            }
        }
        return new EvaluatorCommand(myModel, myTreeBuilder.build(nodeList));
    }
    
    private EvaluatorNode generateNode(String s, String p, Iterator<String> iter)
            throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException,
            SecurityException, ClassNotFoundException {
        if (p.equals("Command")) {
            try {
                return new CommandNode((Command) Class.forName(
                        commandPath + PatternMatcher.checkForMatch(s, myCommandPatterns))
                        .getDeclaredConstructors()[0].newInstance(myModel));
            } catch (ClassNotFoundException | InstantiationException
                    | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | SecurityException e) {
                Command cmd = (Command) myModel.getUserCommand(s);
                if (cmd != null) {
                    return new CommandNode(cmd);
                }
                return new ConstantNode(s);
            }
        } else if (p.equals("Constant")) {
            return new ConstantNode(Double.parseDouble(s));
        } else if (p.equals("ListStart") || p.equals("GroupStart")) {
            blockDepthCount++;
            EvaluatorNode temp = new UserCommandNode(parseIterator(iter));
            List<EvaluatorNode> list = new ArrayList<>();
            list.add(temp);
            return new UserCommandNode(new UserCommand(myModel, list));
        } else if (p.equals("Variable")) {
            return new VariableNode(myModel, s);
        } else {
            return null;
        }
    }
    
    private void removeComment(Iterator<String> iter) {
        while(iter.hasNext()) {
            if (iter.next().equals("\n")) {
                return;                
            }
        }
    }
    
}
