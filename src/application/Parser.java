package application;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import commands.UserCommand;

public class Parser {
    private List<Map.Entry<String, Pattern>> myCommandPatterns;
    private List<Map.Entry<String, Pattern>> mySyntaxPatterns;
    private TreeBuilder myTreeBuilder;
    
    public Parser() {
        myCommandPatterns = makePatterns("resources/languages/English");
        mySyntaxPatterns = makePatterns("resources/languages/Syntax");
    }
    
    public UserCommand parse(String input) {
        
        String[] inputArray = input.split(" ");
        for (String s : inputArray) {
            handleMatchConditions(s, checkSyntax(s, mySyntaxPatterns));
            
        }
        
    }
    
    private boolean match (String input, Pattern regex) {
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
    private String checkSyntax (String s, List<Map.Entry<String, Pattern>> patterns) {
        for (Map.Entry<String, Pattern> p : patterns) {
            if (match(s, p.getValue())) {
                return p.getKey();
            }
        }
        // Maybe throw an exception here
        return null;
    }

    private List<Map.Entry<String, Pattern>> makePatterns (String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(syntax);
        List<Map.Entry<String, Pattern>> patterns = new ArrayList<>();
        Enumeration<String> iter = resources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            System.out.println(resources.getObject(key));
            String regex = resources.getString(key);
            patterns.add(new AbstractMap.SimpleEntry<String, Pattern>(key,
                         // THIS IS THE KEY LINE
                         Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
        return patterns;
    }
    
    private EvaluatorNode handleMatchConditions(String s, String p) {
        if (p.equals("Command")) {
            
        }
    }
}
