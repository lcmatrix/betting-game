package de.bettinggame.configuration;

import de.bettinggame.model.User;
import de.bettinggame.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Custom UserDetailService for Spring Security.
 */
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            User userInstance = user.get();
            UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
            userBuilder.password(userInstance.getPassword());
            userBuilder.authorities(AuthorityUtils.createAuthorityList(userInstance.getRole().name()));
            return userBuilder.build();
        }
        return null;
    }
}
