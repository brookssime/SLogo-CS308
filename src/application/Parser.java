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
        myCommandPatterns = makePatterns("resources/languages/English");
        mySyntaxPatterns = makePatterns("resources/languages/Syntax");
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
            String p = checkForMatch(s, mySyntaxPatterns);
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
                        commandPath + checkForMatch(s, myCommandPatterns))
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

    private boolean match(String input, Pattern regex) {
        // THIS IS THE KEY LINE
        return regex.matcher(input).matches();
        // basic strings can match also, but not using a Pattern
        // return input.matches(regex);
    }

    /**
     * Checks if the given String falls under any of the given patterns
     * 
     * @param s
     * @param patterns
     * @return the pattern name that the String matches, or null if it does not
     *         match a pattern
     */
    private String checkForMatch(String s,
            List<Map.Entry<String, Pattern>> patterns) {
        for (Map.Entry<String, Pattern> p : patterns) {
            if (match(s, p.getValue())) {
                return p.getKey();
            }
        }
        // Maybe throw an exception here
        return null;
    }

    private List<Map.Entry<String, Pattern>> makePatterns(String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(syntax);
        List<Map.Entry<String, Pattern>> patterns = new ArrayList<>();
        Enumeration<String> iter = resources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String regex = resources.getString(key);
            patterns.add(new AbstractMap.SimpleEntry<String, Pattern>(key,
            // THIS IS THE KEY LINE
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
        return patterns;
    }
    
    private void removeComment(Iterator<String> iter) {
        while(iter.hasNext()) {
            if (iter.next().equals("\n")) {
                return;                
            }
        }
    }
    
}
