package jesper.summer.exception;

// InvalidAccessTypeException.java
public class InvalidAccessTypeException extends BusinessException {
    public InvalidAccessTypeException(String type) {
        super("Invalid access type: " + type + ". Must be ENTER or EXIT");
    }
}