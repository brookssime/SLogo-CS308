package syntax;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import tree.Node;
import application.Model;
import application.Parser;

public abstract class SyntaxHandler {
    protected Model myModel;
    protected Parser myParser;
    protected List<Map.Entry<String, Pattern>> myCommandPatterns;

    public SyntaxHandler(Model myModel, Parser myParser,
            List<Map.Entry<String, Pattern>> myCommandPatterns) {
        this.myModel = myModel;
        this.myParser = myParser;
        this.myCommandPatterns = myCommandPatterns;

    }

    public abstract boolean handle(String s, Iterator<String> iter,
            List<Node> nodeList) throws InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, SecurityException,
            ClassNotFoundException;
}
