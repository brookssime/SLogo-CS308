package exceptions;

public class InvalidCommandException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -3835326239959660003L;

    public InvalidCommandException(String s) {
        super(String.format("%s is not a valid command.", s));
    }
}
