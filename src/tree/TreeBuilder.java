// This entire file is part of my masterpiece.
// Sean Scott

package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import application.PatternMatcher;

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
        }
        return nodeList;
    }

    /*private TreeNode generateGroupTree(BlockNode child, CommandNode node, Iterator<TreeNode> iter) {
        List<TreeNode> nodeList = new ArrayList<>();
        child.evaluate().stream().forEach(o -> nodeList.add((TreeNode) o));
        List<TreeNode> cloneList = new ArrayList<>();
        nodeList.stream().forEach(n -> cloneList.add((CommandNode) Class.forName(
                node.getClass().getName()).getDeclaredConstructors()[0]
                .newInstance(myModel)));
        nodeList.add((CommandNode) Class.forName(
                commandPath + PatternMatcher.checkForMatch(s, myCommandPatterns)).getDeclaredConstructors()[0]
                .newInstance(myModel));
        return null;
    }*/

    private void addChildren(CommandNode node, Iterator<TreeNode> iter) {
        for (int i = 0; i < node.getArgNum() && iter.hasNext(); i++) {
            TreeNode child = iter.next();
            node.addChild(child);
            if (child instanceof CommandNode) {
                addChildren((CommandNode) child, iter);
            // If the BlockNode represents a parenthesis group
            /*} else if (child instanceof BlockNode && node.evaluate().size() > 0
                    && !(node.evaluate().get(0) instanceof BlockNode)) {
                node.addChild(generateGroupTree((BlockNode) child, node, iter));
            */}
        }
    }
}
