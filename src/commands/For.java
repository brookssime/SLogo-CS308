package commands;

import java.util.ArrayList;
import java.util.List;

import application.Model;

public class For extends DoTimes {
    public For(Model myModel) {
        super(myModel);
    }

    @Override
    public List<Object> function(List<Object> args) {
        List<Object> firstBlock = new ArrayList<>();
        getRootNodes(args.get(0)).stream().forEach(node -> firstBlock.add(node.evaluate()));
        if (!checkParameters(firstBlock, String.class, double.class, double.class, double.class)) {
            //Throw incorrect parameters exception
        }
        return runForLoop(args, (int) args.get(2), (String) args.get(0), (int) args.get(1), (int) args.get(3));
    }
}
