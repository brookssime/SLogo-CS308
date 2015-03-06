package syntax;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import exceptions.UnbalancedBracketsException;
import tree.BlockNode;
import tree.EvaluatorNode;
import tree.TreeNode;
import application.Model;
import application.Parser;

public class GroupStartHandler extends GroupHandler {

    public GroupStartHandler(Model myModel, Parser myParser,
            List<Entry<String, Pattern>> myCommandPatterns) {
        super(myModel, myParser, myCommandPatterns);
    }

    @Override
    public boolean handle(String s, Iterator<String> iter,
            List<TreeNode> nodeList) throws InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        setGroupDepthCount(getGroupDepthCount() + 1);
        TreeNode temp = myParser.parseIterator(iter);
        if (temp instanceof EvaluatorNode) {
            throw new UnbalancedBracketsException();
        }
        List<TreeNode> list = new ArrayList<>();
        list.add(temp);
        return nodeList.add(new BlockNode(list));
    }

}
