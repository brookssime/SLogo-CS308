package exceptions;

public class InvalidWindowBehaviorException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -8861707152366819003L;

    public InvalidWindowBehaviorException (String s) {
        super(String.format("%s is not a valid window behavior", s));
    }
}
