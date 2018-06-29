package de.bettinggame.infrastructure;

import de.bettinggame.domain.User;
import de.bettinggame.domain.enums.UserStatus;
import de.bettinggame.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

/**
 * Custom UserDetailService for Spring Security.
 */
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userOrEMail) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsernameOrEmail(userOrEMail, userOrEMail);
        User userInstance = user.orElseThrow(() -> {
            return new UsernameNotFoundException("User: " + userOrEMail + " not found");
        });
        UserBuilder userBuilder = withUsername(userInstance.getUsername());
        userBuilder.password(userInstance.getPassword());
        userBuilder.disabled(userInstance.getStatus() != UserStatus.ACTIVE);
        userBuilder.accountLocked(userInstance.getStatus() == UserStatus.LOCKED);
        userBuilder.authorities(AuthorityUtils.createAuthorityList(userInstance.getRole().name()));
        return userBuilder.build();
    }
}
