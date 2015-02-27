package commands;

import java.util.List;

public class Repeat extends Command {

    @Override
    public List<Object> function(List<Object> args) {
        
        for (int i = 0; i < (int) args.get(0); i++) {
            
        }
    }

}
