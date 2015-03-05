package syntax;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import tree.ConstantNode;
import tree.Node;
import application.Model;
import application.Parser;

public class ConstantHandler extends SyntaxHandler {

    public ConstantHandler(Model myModel, Parser myParser,
            List<Map.Entry<String, Pattern>> myCommandPatterns) {
        super(myModel, myParser, myCommandPatterns);
    }
    
    @Override
    public boolean handle(String s, Iterator<String> iter,
            List<Node> nodeList) throws InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, SecurityException,
            ClassNotFoundException {
        return nodeList.add(new ConstantNode(Double.parseDouble(s)));
    }

}
