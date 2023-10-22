package exception;

public class DuplicateSymbolException extends Throwable {
    public DuplicateSymbolException(String symbolsMustBeUnique) {
        super(symbolsMustBeUnique);
    }
}
