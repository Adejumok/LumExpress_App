package africa.semicolon.lumexpress.security.providers;

import africa.semicolon.lumexpress.security.LumExpressUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LumExpressUserAuthenticationProvider implements AuthenticationProvider {
    private final LumExpressUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername((String) authentication.getPrincipal());
        if (userDetails!= null){
            if (isPasswordMatch(authentication, userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                                authentication.getCredentials(),
                                userDetails.getAuthorities());
                return authenticationToken;
            }
            throw new BadCredentialsException("Incorrect Password");
        }
        throw new AuthenticationCredentialsNotFoundException("Email does not exist");
    }

    private boolean isPasswordMatch(Authentication authentication, UserDetails userDetails){
        return passwordEncoder.matches((String) authentication.getCredentials(), userDetails.getPassword());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        var appAuthType =
                UsernamePasswordAuthenticationToken.class;
        return authentication.equals(appAuthType);
    }
}
