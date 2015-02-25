package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreeBuilder {

    public TreeBuilder() {

    }

    public List<EvaluatorNode> build(List<EvaluatorNode> list) {
        List<EvaluatorNode> nodeList = new ArrayList<>();
        Iterator<EvaluatorNode> iter = list.iterator();
        while (iter.hasNext()) {
            EvaluatorNode node = iter.next();
            if (node instanceof CommandNode) {
                nodeList.add(node);
                addChildren((CommandNode) node, iter);
            }
            else {
                //throw an exception? No command node likely means the input string was wrong
                //Possible to start with a group bracket [] ()
            }
        }
        return nodeList;
    }

    private void addChildren(CommandNode node, Iterator<EvaluatorNode> iter) {
        for (int i = 0; i < node.countChildren() && iter.hasNext(); i++) {
            EvaluatorNode child = iter.next();
            if (node instanceof CommandNode) {
                addChildren((CommandNode) child, iter);
            } else {
                node.addChild(child);
            }
        }
    }
}
