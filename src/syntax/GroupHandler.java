package syntax;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import application.Model;
import application.Node;
import application.Parser;

public abstract class GroupHandler extends SyntaxHandler {
    private static int groupDepthCount = 0;

    public GroupHandler(Model myModel, Parser myParser,
            List<Entry<String, Pattern>> myCommandPatterns) {
        super(myModel, myParser, myCommandPatterns);
    }
    
    protected static int getGroupDepthCount() {
        return groupDepthCount;
    }

    protected static void setGroupDepthCount(int groupDepthCount) {
        GroupHandler.groupDepthCount = groupDepthCount;
    }

    @Override
    public abstract boolean handle(String s, Iterator<String> iter,
            List<Node> nodeList) throws InstantiationException,
            IllegalAccessException, InvocationTargetException,
            ClassNotFoundException;

}
