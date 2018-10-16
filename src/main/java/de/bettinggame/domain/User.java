package de.bettinggame.domain;

import de.bettinggame.domain.enums.UserRole;
import de.bettinggame.domain.enums.UserStatus;
import de.bettinggame.domain.repository.UserRepository;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

/**
 * User class.
 */
@Entity
@Table(name = "user")
public class User extends AbstractIdEntity {

    @NotNull
    @Size(max = 50, message = "field.max.50.characters")
    @Column(nullable = false)
    private String username;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Size(max = 200, message = "field.max.200.characters")
    @Column(nullable = false)
    private String email;

    @Size(max = 200, message = "field.max.200.characters")
    @Column(nullable = false)
    private String firstname;

    @Size(max = 200, message = "field.max.200.characters")
    @Column(nullable = false)
    private String surname;

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

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public UserStatus getStatus() {
        return status;
    }

    private void setStatus(UserStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("UserStatus was null");
        }
        this.status = status;
    }

    public UserRole getRole() {
        return role;
    }

    public Optional<String> getFullname() {
        if (firstname == null) {
            return Optional.ofNullable(firstname);
        }

        String name = firstname;
        name += (surname != null) ? " " + surname : "";
        return Optional.of(name);
    }

    public void create(UserRepository repo) {
        repo.save(this);
    }

    public void updateData(
            String username,
            String firstname,
            String surname,
            String email,
            UserRole role,
            UserStatus status) {
        this.username = username;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.role = role;
        setStatus(status);
    }

    public void lock() {
        setStatus(UserStatus.LOCKED);
    }

    public void unlock() {
        setStatus(UserStatus.ACTIVE);
    }
}
