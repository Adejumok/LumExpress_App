package africa.semicolon.lumexpress.security;

import africa.semicolon.lumexpress.data.models.LumExpressUser;
import africa.semicolon.lumexpress.data.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("here");
        LumExpressUser foundUser = userService.getUserByUserName(username);
        log.info("user->{}", foundUser);
        return new SecureUser(foundUser);
    }
}
