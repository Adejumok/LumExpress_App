package africa.semicolon.lumexpress.exception;

public class CartNotFoundException extends Exception {
    public CartNotFoundException(String message, Long cartId) {
        super(message);
    }
}
