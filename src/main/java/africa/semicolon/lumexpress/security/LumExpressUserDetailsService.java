package africa.semicolon.lumexpress.security;

import africa.semicolon.lumexpress.data.models.LumExpressUser;
import africa.semicolon.lumexpress.data.service.UserService;
import africa.semicolon.lumexpress.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LumExpressUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        LumExpressUser lumExpressUser = null;
        try {
            lumExpressUser = userService.getUserByUserName(username);
            return new SecureUser(lumExpressUser);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
