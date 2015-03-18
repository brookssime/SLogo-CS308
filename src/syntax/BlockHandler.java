package syntax;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import tree.TreeNode;
import application.Model;
import application.Parser;

public abstract class BlockHandler extends SyntaxHandler {
    private static int groupDepthCount = 0;
    private static int listDepthCount = 0;

    public BlockHandler(Model myModel, Parser myParser,
            List<Entry<String, Pattern>> myCommandPatterns) {
        super(myModel, myParser, myCommandPatterns);
    }
    
    protected static int getGroupDepthCount() {
        return groupDepthCount;
    }

    protected static void setGroupDepthCount(int groupDepthCount) {
        BlockHandler.groupDepthCount = groupDepthCount;
    }
    
    protected static int getListDepthCount() {
        return listDepthCount;
    }

    protected static void setListDepthCount(int listDepthCount) {
       BlockHandler.listDepthCount = listDepthCount;
    }

    @Override
    public abstract boolean handle(String s, Iterator<String> iter,
            List<TreeNode> nodeList) throws InstantiationException,
            IllegalAccessException, InvocationTargetException,
            ClassNotFoundException;

}
