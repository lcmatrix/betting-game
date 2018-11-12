package de.bettinggame.application.admin;

import de.bettinggame.domain.User;
import de.bettinggame.domain.enums.UserRole;
import de.bettinggame.domain.enums.UserStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Command to edit and change a user.
 */
public class EditUserCommand {
    @NotBlank(message = "field.not.blank")
    @Size(max = 50, message = "field.max.50.characters")
    private String username;

    @NotBlank(message = "field.not.blank")
    @Size(max = 200, message = "field.max.200.characters")
    private String email;

    @Size(max = 200, message = "field.max.200.characters")
    private String firstname;

    @Size(max = 200, message = "field.max.200.characters")
    private String surname;

    @NotNull(message = "field.not.blank")
    private UserStatus status;

    @NotNull(message = "field.not.blank")
    private UserRole role;

    public EditUserCommand() {

    }

    public EditUserCommand(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstname = user.getFirstname();
        this.surname = user.getSurname();
        this.status = user.getStatus();
        this.role = user.getRole();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
