package commands;

import java.util.ArrayList;
import java.util.List;

import exceptions.IncorrectParametersException;
import application.Model;
import application.Turtle;

public class For extends DoTimes {
    public For(Model myModel) {
        super(myModel);
    }

    @Override
    public List<Object> function(Turtle myTurtle, List<Object> args) {
        List<Object> firstBlock = new ArrayList<>();
        getRootNodes(args.get(0)).stream().forEach(node -> firstBlock.add(node.evaluate()));
        checkParameters(firstBlock, String.class, double.class, double.class, double.class);
        return runForLoop(args, ((Double) args.get(2)).intValue(), (String) args.get(0), ((Double) args.get(1)).intValue(), ((Double) args.get(3)).intValue());
    }
}
