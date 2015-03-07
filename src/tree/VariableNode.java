package tree;

import java.util.ArrayList;
import java.util.List;

import application.Model;

public class VariableNode extends TreeNode {

    private String myString;
    private static Model myModel;
    private Integer myIndex;
    
    public VariableNode(Model myModel, String myString) {
        this.myModel = myModel;
        this.myString = myString;
    }
    
    public VariableNode(String myString) {
        this.myString = myString;
    }
    @Override
    public List<Object> evaluate(List<Object> args) {
        if (myIndex != null) {
            List<Object> list = new ArrayList<>();
            System.out.println("Size: " + args.size());
            System.out.println("Index: " + myIndex);
            list.add(args.get(myIndex));
            return list;
        }
        List<Object> list = new ArrayList<>();
        Double d = myModel.getVariableValue(myString);
        if (d == null) {
            list.add(myString);
        } else {
            list.add(d.doubleValue());
        }
        return list;
    }

    @Override
    public int countVariables() {
        if (myIndex == null) {
            return 0;
        }
        return 1;
    }

    @Override
    public List<VariableNode> getVariableNodes() {
        List<VariableNode> list = new ArrayList<>();
        list.add(this);
        return list;
    }
    
    public void setIndex(int i) {
        myIndex = i;
    }

}
