package application;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class PatternMatcher {
    private static boolean match(String input, Pattern regex) {
        return regex.matcher(input).matches();
    }

    /**
     * Checks if the given String falls under any of the given patterns
     * 
     * @param s
     * @param patterns
     * @return the pattern name that the String matches, or null if it does not
     *         match a pattern
     */
    public static String checkForMatch(String s,
            List<Map.Entry<String, Pattern>> patterns) {
        for (Map.Entry<String, Pattern> p : patterns) {
            if (match(s, p.getValue())) {
                return p.getKey();
            }
        }
        // Maybe throw an exception here
        return null;
    }

    public static List<Map.Entry<String, Pattern>> makePatterns(String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(syntax);
        List<Map.Entry<String, Pattern>> patterns = new ArrayList<>();
        Enumeration<String> iter = resources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String regex = resources.getString(key);
            patterns.add(new AbstractMap.SimpleEntry<String, Pattern>(key,
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
        return patterns;
    }
}
