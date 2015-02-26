package application;

import java.util.ArrayList;
import java.util.List;

public class VariableNode extends EvaluatorNode {

    private String myString;
    private Model myModel;
    private Integer myIndex;
    
    public VariableNode(Model myModel, String myString) {
        this.myModel = myModel;
        this.myString = myString;
    }
    
    @Override
    public List<Object> evaluate(List<Object> args) {
        if (myIndex != null) {
            System.out.println("Am I here?");
            List<Object> list = new ArrayList<>();
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
        System.out.println("Here?");
        myIndex = i;
    }

}
