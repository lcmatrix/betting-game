package de.bettinggame.application.registration;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.bettinggame.domain.Identity;
import de.bettinggame.domain.user.User;
import de.bettinggame.domain.user.UserRole;
import de.bettinggame.domain.user.UserStatus;

/**
 * Form object for registration.
 */
public class RegisterUserCommand {
    @NotNull
    @Size(min = 1, max = 50, message = "registration.form.username.size")
    private String username;
    @NotNull
    @Size(min = 6, message = "registration.form.password.size")
    private String password;
    @NotNull
    @Size(min = 1, max = 200, message = "registration.form.email.size")
    private String email;

    public RegisterUserCommand() {

    }

    public RegisterUserCommand(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User createUser(String identifier) {
        return new User(new Identity(identifier), username, email, UserStatus.PENDING, UserRole.USER);
    }
}
