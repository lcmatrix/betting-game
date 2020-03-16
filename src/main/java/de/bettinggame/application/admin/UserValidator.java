package de.bettinggame.application.admin;

import de.bettinggame.domain.user.User;
import de.bettinggame.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return true; //EditUserCommand.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //EditUserCommand object = (EditUserCommand) target;
        Optional<User> user = userRepository.findByUsername(target.toString()); //object.getUsername());
        user.ifPresent(u ->
                errors.rejectValue("username", "error.user.username.exists", "Username already in use"));
        //User a = userRepository.findByEmail(target.toString()); //object.getEmail());
        user.ifPresent(u ->
                errors.rejectValue("email", "error.user.email.exists", "E-Mail address already exists"));
    }
}
