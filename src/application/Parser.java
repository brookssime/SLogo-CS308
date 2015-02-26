package application;

import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import commands.*;

public class Parser {
    private List<Map.Entry<String, Pattern>> myCommandPatterns;
    private List<Map.Entry<String, Pattern>> mySyntaxPatterns;
    private Map<String, UserCommand> customCommandMap;
    private Model myModel;
    private static final String commandPath = "commands.";
    private TreeBuilder myTreeBuilder;
    //private List<String> toCommandVariables;
    private int blockDepthCount = 0;

    public Parser(Model myModel) {
        myCommandPatterns = makePatterns("resources/languages/English");
        mySyntaxPatterns = makePatterns("resources/languages/Syntax");
        customCommandMap = new HashMap<String, UserCommand>();
        myTreeBuilder = new TreeBuilder();
        this.myModel = myModel;
        //toCommandVariables = new ArrayList<>();
        
    }

    public EvaluatorCommand parse(String input) throws InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        String[] inputArray = input.split(" ");
        Iterator<String> iter = Arrays.asList(inputArray).iterator();
        EvaluatorCommand myEvaluatorCommand = (EvaluatorCommand) parseIterator(iter);
        if (iter.hasNext()) {
            //Throw and error here "Closing brackets were placed with no corresponding opening brackets"
        }
        return myEvaluatorCommand;
    }

    private NodesCommand parseIterator(Iterator<String> iter)
            throws InstantiationException, IllegalAccessException,
            InvocationTargetException, ClassNotFoundException {
        List<EvaluatorNode> list = new ArrayList<>();
        while(iter.hasNext()) {
            String s = iter.next();
            String p = checkForMatch(s, mySyntaxPatterns);
            if (p == null) {
                //do nothing
            } else if (p.equals("ListEnd") || p.equals("GroupEnd")) {
                if (blockDepthCount > 0) {
                    break;
                }
                else {
                    //Throw an uneven bracket count error
                }
            } else if (p.equals("Comment")) {
                removeComment(iter);
            } else {
                list.add(generateNode(s, p, iter));
            }
        }
        if (blockDepthCount > 0) {
            blockDepthCount--;
            return new UserCommand(myModel, myTreeBuilder.build(list));
        }
        return new EvaluatorCommand(myModel, myTreeBuilder.build(list));
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
                return new CommandNode((Command) myModel.getUserCommand(s));
            }
        } else if (p.equals("Constant")) {
            return new ConstantNode(Double.parseDouble(s));
        } else if (p.equals("ListStart") || p.equals("GroupStart")) {
            blockDepthCount++;
            return new CommandNode(parseIterator(iter));
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
    
    /*private void handleToCommand(Iterator<String> iter) throws InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        String commandName = iter.next();
        iter.next();
        List<Object> varList = parseIterator(iter).process(new ArrayList<>());
        List<String> stringVarList = new ArrayList<>();
        for (Object o : varList) {
            stringVarList.add((String) o);
            //Throw error here "must use nonexistent variables to define a command's variable"
        }
        toCommandVariables.addAll(stringVarList);
        iter.next();
        customCommandMap.put(commandName, parseIterator(iter));
        toCommandVariables.removeAll(stringVarList);       
    }*/
    
}
