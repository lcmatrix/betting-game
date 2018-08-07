package de.bettinggame.application.admin;

import de.bettinggame.domain.User;
import de.bettinggame.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void updateUser(EditUserCommand userCommand, String userId) {
        Optional<User> persistentUser = userRepository.findByUsername(userId);
        persistentUser.ifPresent(u -> u.updateData(userCommand.getUsername(), userCommand.getFirstname(),
                userCommand.getSurname(), userCommand.getEmail(), userCommand.getRole(), userCommand.getStatus()));
        LOG.info("Changed user data for user [{}]", userId);
    }
}
