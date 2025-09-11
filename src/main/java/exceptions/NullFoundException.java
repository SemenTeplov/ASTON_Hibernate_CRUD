package exceptions;

public class NullFoundException extends RuntimeException {
    public NullFoundException() {
        super("Found null");
    }
}
