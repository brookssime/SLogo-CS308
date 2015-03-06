package syntax;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import exceptions.UnbalancedBracketsException;
import tree.TreeNode;
import application.Model;
import application.Parser;

public class GroupEndHandler extends GroupHandler {

    public GroupEndHandler(Model myModel, Parser myParser,
            List<Entry<String, Pattern>> myCommandPatterns) {
        super(myModel, myParser, myCommandPatterns);
    }

    @Override
    public boolean handle(String s, Iterator<String> iter, List<TreeNode> nodeList)
            throws InstantiationException, IllegalAccessException,
            InvocationTargetException, ClassNotFoundException {
        if (getGroupDepthCount() > 0) {
            setGroupDepthCount(getGroupDepthCount() - 1);
        } else {
            throw new UnbalancedBracketsException();
        }
        return false;
    }

}
