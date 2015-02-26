package application;

import java.util.ArrayList;
import java.util.List;

public class VariableNode extends EvaluatorNode {

    private String myString;
    private Model myModel;
    
    public VariableNode(Model myModel, String myString) {
        this.myString = myString;
    }
    
    @Override
    public List<Object> evaluate(List<Object> args) {
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
        return 0;
    }

}
