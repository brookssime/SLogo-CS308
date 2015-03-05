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
import tree.Node;
import tree.TreeBuilder;
import commands.*;

public class Parser {
    private List<Map.Entry<String, Pattern>> myCommandPatterns;
    private List<Map.Entry<String, Pattern>> mySyntaxPatterns;
    private Model myModel;
    private TreeBuilder myTreeBuilder;

    public Parser(Model myModel) {
        this.myModel = myModel;
        myCommandPatterns = PatternMatcher.makePatterns("resources/languages/" + myModel.getLanguage());
        mySyntaxPatterns = PatternMatcher.makePatterns("resources/languages/Syntax");
        myTreeBuilder = new TreeBuilder();        
    }
    
    public void updateCommandPatterns() {
    	myCommandPatterns = PatternMatcher.makePatterns("resources/languages/" + myModel.getLanguage());
    }
    
    public CommandNode parse(String input) throws InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        return (CommandNode) parseIterator(Arrays.asList(input.split(" ")).iterator());
    }
    
    public Node parseIterator(Iterator<String> iter)
            throws InstantiationException, IllegalAccessException,
            InvocationTargetException, ClassNotFoundException {
        List<Node> nodeList = new ArrayList<>();
        while (iter.hasNext()) {
            String s = iter.next();
            String p = PatternMatcher.checkForMatch(s, mySyntaxPatterns);
            if (p == null) {
                continue;
            }
            SyntaxHandler mySyntaxHandler = (SyntaxHandler) Class.forName(
                    String.format("syntax.%sHandler", p))
                    .getDeclaredConstructors()[0].newInstance(myModel, this, myCommandPatterns);
            if (!mySyntaxHandler.handle(s, iter, nodeList)) {
                return new BlockNode(myTreeBuilder.build(nodeList));
            }
        }
        return new EvaluatorNode(myModel, myTreeBuilder.build(nodeList));
    }
    
}
