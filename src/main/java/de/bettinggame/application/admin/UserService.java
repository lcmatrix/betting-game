package de.bettinggame.application.admin;

import de.bettinggame.application.registration.RegisterUserCommand;
import de.bettinggame.domain.User;
import de.bettinggame.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(RegisterUserCommand registerUserCommand) {
        User user = registerUserCommand.createUser();
        user.updatePassword(passwordEncoder.encode(registerUserCommand.getPassword()));
        userRepository.save(user);
        LOG.info("New user registered [{}]", registerUserCommand.getUsername());
    }

    @Transactional
    public void updateUser(EditUserCommand userCommand, String userId) {
        Optional<User> persistentUser = userRepository.findByUsername(userId);
        if (!persistentUser.isPresent()) {
            throw new EntityNotFoundException();
        }
        persistentUser.ifPresent(u -> u.updateData(userCommand.getUsername(), userCommand.getFirstname(),
                userCommand.getSurname(), userCommand.getEmail(), userCommand.getRole(), userCommand.getStatus()));
        LOG.info("Changed user data for user [{}]", userId);
    }

    @Transactional
    public void lockUser(String userId) {
        Optional<User> user = userRepository.findByUsername(userId);
        if (!user.isPresent()) {
            throw new EntityNotFoundException();
        }
        user.ifPresent(User::lock);
        LOG.info("Locked user [{}]", userId);
    }

    @Transactional
    public void unlockUser(String userId) {
        Optional<User> user = userRepository.findByUsername(userId);
        if (!user.isPresent()) {
            throw new EntityNotFoundException();
        }
        user.ifPresent(User::unlock);
        LOG.info("Unlock user [{}]", userId);
    }
}
