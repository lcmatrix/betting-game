package de.bettinggame.model;

import de.bettinggame.model.enums.UserRole;
import de.bettinggame.model.enums.UserStatus;
import de.bettinggame.model.repository.UserRepository;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * User class.
 */
@Entity
@Table(name = "users")
public class User extends AbstractIdEntity {

    @NotNull
    @Column(nullable = false)
    private String username;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    public User() {
    }

    public User(String username, String password, String email, UserStatus status, UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public UserRole getRole() {
        return role;
    }

    public void create(UserRepository repo) {
        repo.save(this);
    }
}
