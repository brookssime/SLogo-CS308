package syntax;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import application.BlockNode;
import application.Model;
import application.Node;
import application.Parser;

public class GroupStartHandler extends GroupHandler {

    public GroupStartHandler(Model myModel, Parser myParser,
            List<Entry<String, Pattern>> myCommandPatterns) {
        super(myModel, myParser, myCommandPatterns);
    }

    @Override
    public boolean handle(String s, Iterator<String> iter,
            List<Node> nodeList) throws InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        setGroupDepthCount(getGroupDepthCount() + 1);
        Node temp = myParser.parseIterator(iter);
        List<Node> list = new ArrayList<>();
        list.add(temp);
        return nodeList.add(new BlockNode(list));
    }

}
