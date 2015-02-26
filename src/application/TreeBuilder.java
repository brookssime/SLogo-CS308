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

    private void addChildren(CommandNode node, Iterator<EvaluatorNode> iter) {
        for (int i = 0; i < node.getMaxChildren() && iter.hasNext(); i++) {
            EvaluatorNode child = iter.next();
            node.addChild(child);
            if (child instanceof CommandNode) {
                addChildren((CommandNode) child, iter);
            }
        }
    }
}
