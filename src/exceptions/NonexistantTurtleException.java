package exceptions;

public class NonexistantTurtleException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -1964888766205515111L;

    public NonexistantTurtleException(Double id) {
        super(String.format("Turtle $i does not exist", id.intValue()));
    }
}
