package exceptions;

public class UnbalancedBracketsException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -1596709668769187255L;

    public UnbalancedBracketsException() {
        super("Please balance all brackets in the input string.");
    }
}
