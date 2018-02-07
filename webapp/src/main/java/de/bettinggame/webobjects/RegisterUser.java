package de.bettinggame.webobjects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.bettinggame.model.User;
import de.bettinggame.model.enums.UserRole;
import de.bettinggame.model.enums.UserStatus;

/**
 * Form object for registration.
 */
public class RegisterUser {
    @NotNull
    @Size(min = 1, max = 50, message = "register.form.username.size")
    private String username;
    @NotNull
    @Size(min = 6, message = "register.form.password.size")
    private String password;
    @NotNull
    @Size(min = 1, max = 200, message = "register.form.email.size")
    private String email;

    public RegisterUser() {

    }

    public RegisterUser(String username, String password, String email) {
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

    public User createUser() {
        User user = new User(username, password, email, UserStatus.PENDING, UserRole.USER);
        return user;
    }
}
