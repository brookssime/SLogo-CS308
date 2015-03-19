package syntax;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import commands.MakeVariable;
import tree.ConstantNode;
import tree.TreeNode;
import tree.VariableNode;
import application.Model;
import application.Parser;

public class VariableHandler extends SyntaxHandler {

    public VariableHandler(Model myModel, Parser myParser,
            List<Entry<String, Pattern>> myCommandPatterns) {
        super(myModel, myParser, myCommandPatterns);
    }

    @Override
    public boolean handle(String s, Iterator<String> iter, List<TreeNode> nodeList)
            throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException,
            SecurityException, ClassNotFoundException {
        if (nodeList.size() > 0 && nodeList.get(nodeList.size() - 1) instanceof MakeVariable) {
            return nodeList.add(new ConstantNode(s));
        }
        return nodeList.add(new VariableNode(getModel(), s));
    }

}
