package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreeBuilder {

    public TreeBuilder() {

    }

    public List<TreeNode> build(List<TreeNode> list) {
        List<TreeNode> nodeList = new ArrayList<>();
        Iterator<TreeNode> iter = list.iterator();
        while (iter.hasNext()) {
            TreeNode node = iter.next();
            nodeList.add(node);
            if (node instanceof CommandNode) {
                addChildren((CommandNode) node, iter);
            }
            else {
                //throw an exception?
            }
        }
        return nodeList;
    }

    private void addChildren(CommandNode node, Iterator<TreeNode> iter) {
        for (int i = 0; i < node.getArgNum() && iter.hasNext(); i++) {
            TreeNode child = iter.next();
            node.addChild(child);
            if (child instanceof CommandNode) {
                addChildren((CommandNode) child, iter);
            }
        }
    }
}
