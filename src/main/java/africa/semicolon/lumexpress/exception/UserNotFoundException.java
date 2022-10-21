package africa.semicolon.lumexpress.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String format) {
        super(format);
    }
}
