package exception;

public class InvalidTypeException extends Throwable {
    public InvalidTypeException(String typeCannotBeNull) {
        super(typeCannotBeNull);
    }
}
