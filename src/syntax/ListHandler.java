package syntax;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import tree.Node;
import application.Model;
import application.Parser;

public abstract class ListHandler extends SyntaxHandler {
    private static int listDepthCount = 0;

    public ListHandler(Model myModel, Parser myParser,
            List<Entry<String, Pattern>> myCommandPatterns) {
        super(myModel, myParser, myCommandPatterns);
    }

    protected static int getListDepthCount() {
        return listDepthCount;
    }

    protected static void setListDepthCount(int listDepthCount) {
        ListHandler.listDepthCount = listDepthCount;
    }

    @Override
    public abstract boolean handle(String s, Iterator<String> iter,
            List<Node> nodeList) throws InstantiationException,
            IllegalAccessException, InvocationTargetException,
            ClassNotFoundException;

}
