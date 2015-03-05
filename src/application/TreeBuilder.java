package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import commands.CommandNode;

public class TreeBuilder {

    public TreeBuilder() {

    }

    public List<Node> build(List<Node> list) {
        List<Node> nodeList = new ArrayList<>();
        Iterator<Node> iter = list.iterator();
        while (iter.hasNext()) {
            Node node = iter.next();
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

    private void addChildren(CommandNode node, Iterator<Node> iter) {
        for (int i = 0; i < node.getArgNum() && iter.hasNext(); i++) {
            Node child = iter.next();
            node.addChild(child);
            if (child instanceof CommandNode) {
                addChildren((CommandNode) child, iter);
            }
        }
    }
}
