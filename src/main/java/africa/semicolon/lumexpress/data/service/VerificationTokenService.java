package africa.semicolon.lumexpress.data.service;

import africa.semicolon.lumexpress.data.models.VerificationToken;
import africa.semicolon.lumexpress.exception.VerificationTokenException;

public interface VerificationTokenService {
    VerificationToken createToken(String userEmail);
    boolean isValidVerificationToken (String token) throws VerificationTokenException;

}
