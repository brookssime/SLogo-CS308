package application;

import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import commands.*;

public class Parser {
    private List<Map.Entry<String, Pattern>> myCommandPatterns;
    private List<Map.Entry<String, Pattern>> mySyntaxPatterns;
    private Model myModel;
    private static String commandPath = "commands.";
    private TreeBuilder myTreeBuilder;

    public Parser(Model myModel) {
        myCommandPatterns = makePatterns("resources/languages/English");
        mySyntaxPatterns = makePatterns("resources/languages/Syntax");
        myTreeBuilder = new TreeBuilder();
        this.myModel = myModel;
    }

    public UserCommand parse(String input) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, ClassNotFoundException {
        List<EvaluatorNode> list = new ArrayList<>();
        String[] inputArray = input.split(" ");
        for (String s : inputArray) {
            list.add(handleMatchConditions(s,
                    checkForMatch(s, mySyntaxPatterns)));
        }
        return new UserCommand(myModel, myTreeBuilder.build(list));
    }

    private boolean match(String input, Pattern regex) {
        // THIS IS THE KEY LINE
        return regex.matcher(input).find();
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

    private EvaluatorNode handleMatchConditions(String s, String p)
            throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException,
            SecurityException, ClassNotFoundException {
        if (p.equals("Command")) {
            return new CommandNode((Command) Class.forName(
                    commandPath + checkForMatch(s, myCommandPatterns))
                    .getDeclaredConstructors()[0].newInstance(myModel));
        } else if (p.equals("Constant")) {
            return new ConstantNode(Double.parseDouble(s));
        } else {
            return new ConstantNode(s);
        }
    }
}
