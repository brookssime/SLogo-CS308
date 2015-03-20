// This entire file is part of my masterpiece.
// Thomas Puglisi

package application;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import syntax.SyntaxHandler;
import tree.BlockNode;
import tree.CommandNode;
import tree.EvaluatorNode;
import tree.TreeNode;
import tree.TreeBuilder;
import commands.*;

public class Parser {
    private List<Map.Entry<String, Pattern>> myCommandPatterns;
    private List<Map.Entry<String, Pattern>> mySyntaxPatterns;
    private Model myModel;
    private static final String LANGUAGE_PATH = "resources/languages/";
    private static final String SYNTAX_PATH = "resources/languages/Syntax";

    public Parser(Model myModel) {
        this.myModel = myModel;
        updateCommandPatterns();
        mySyntaxPatterns = PatternMatcher.makePatterns(SYNTAX_PATH);        
    }
    
    public void updateCommandPatterns() {
    	myCommandPatterns = PatternMatcher.makePatterns(LANGUAGE_PATH + myModel.getLanguage());
    }
    
    /**
     * Parses an input string and returns a CommandNode for easy evaluation and
     * command history storage
     * 
     * @param input
     *            input string from the user
     * @return an EvaluatorNode cast as a CommandNode, which can be easily
     *         evaluated
     */
    public CommandNode parse(String input) throws InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        return (CommandNode) parseIterator(Arrays.asList(input.split(" ")).iterator());
    }
    
    /**
     * Generates a list of TreeNodes and puts them into a tree using TreeBuilder
     * 
     * @param iter
     *            Iterator of string tokens taken from a user input
     * @return either a BlockNode if called from a Group- or ListStartHandler,
     *         or an EvaluatorNode if called from the parse method
     */
    public TreeNode parseIterator(Iterator<String> iter)
            throws InstantiationException, IllegalAccessException,
            InvocationTargetException, ClassNotFoundException {
        List<TreeNode> nodeList = new ArrayList<>();
        while (iter.hasNext()) {
            String token = iter.next();
            String syntaxType = PatternMatcher.checkForMatch(token, mySyntaxPatterns);
            if (syntaxType == null) {
                continue;
            }
            SyntaxHandler mySyntaxHandler = (SyntaxHandler) Class.forName(
                    String.format("syntax.%sHandler", syntaxType))
                    .getDeclaredConstructors()[0].newInstance(myModel, this, myCommandPatterns);
            if (!mySyntaxHandler.handle(token, iter, nodeList)) {
                return new BlockNode(TreeBuilder.build(nodeList));
            }
        }
        return new EvaluatorNode(myModel, TreeBuilder.build(nodeList));
    }
    
}
