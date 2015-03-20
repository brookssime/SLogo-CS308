// This entire file is part of my masterpiece.
// Thomas Puglisi

package syntax;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import tree.TreeNode;
import application.Model;
import application.Parser;

/**
 * Each subclass handles the correct iteration, node creation, or parse call
 * depending on the syntax type.
 * 
 * There exists a subclass of SyntaxHandler for each syntax type as described in
 * the syntax resource bundle.
 * 
 * A new subclass may be created for any new syntax types as needed
 * 
 * @author Tom
 * 
 */
public abstract class SyntaxHandler {
    private Model myModel;
    private Parser myParser;
    private List<Map.Entry<String, Pattern>> myCommandPatterns;
    
    public Model getModel() {
        return myModel;
    }

    public Parser getParser() {
        return myParser;
    }

    public List<Map.Entry<String, Pattern>> getCommandPatterns() {
        return myCommandPatterns;
    }

    public SyntaxHandler(Model myModel, Parser myParser,
            List<Map.Entry<String, Pattern>> myCommandPatterns) {
        this.myModel = myModel;
        this.myParser = myParser;
        this.myCommandPatterns = myCommandPatterns;
    }

    /**
     * Adds the appropriate node (or nodes) to nodeList based on the syntax type
     * of the token
     * 
     * @param token
     *            the most recent token pulled from iter
     * @param iter
     *            iterator containing all tokens from the user's input string
     * @param nodeList
     *            list of nodes to be formed into a tree by the TreeBuilder
     * @return true if the token was completely handled
     */
    public abstract boolean handle(String token, Iterator<String> iter,
            List<TreeNode> nodeList) throws InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, SecurityException,
            ClassNotFoundException;
}
