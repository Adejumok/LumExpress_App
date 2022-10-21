package africa.semicolon.lumexpress.exception;

import africa.semicolon.lumexpress.data.models.VerificationToken;

public class VerificationTokenException extends Exception{
    public VerificationTokenException (String message){
        super(message);
    }
}
