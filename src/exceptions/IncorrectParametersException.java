package exceptions;

public class IncorrectParametersException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -8861707152366819003L;

    public IncorrectParametersException(String s) {
        super(String.format("Incorrect parameters detected for the %s command ", s.substring(s.indexOf(".") + 1, s.length())));
    }
}
