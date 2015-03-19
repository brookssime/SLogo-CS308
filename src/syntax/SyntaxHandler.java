package syntax;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import tree.TreeNode;
import application.Model;
import application.Parser;

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

    public abstract boolean handle(String s, Iterator<String> iter,
            List<TreeNode> nodeList) throws InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, SecurityException,
            ClassNotFoundException;
}
