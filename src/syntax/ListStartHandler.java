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

public class ListStartHandler extends ListHandler {

    public ListStartHandler(Model myModel, Parser myParser,
            List<Entry<String, Pattern>> myCommandPatterns) {
        super(myModel, myParser, myCommandPatterns);
    }

    @Override
    public boolean handle(String s, Iterator<String> iter,
            List<TreeNode> nodeList) throws InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        setListDepthCount(getListDepthCount() + 1);
        TreeNode temp = myParser.parseIterator(iter);
        if (temp instanceof EvaluatorNode) {
            throw new UnbalancedBracketsException();
        }
        //Lists are double-wrapped with BlockNodes so the parent node receives a BlockNode upon evaluating its child
        List<TreeNode> list = new ArrayList<>();
        list.add(temp);
        return nodeList.add(new BlockNode(list));
    }

}
