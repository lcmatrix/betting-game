package de.bettinggame.model;

import de.bettinggame.model.repository.UserRepository;

import javax.persistence.*;

/**
 * User class.
 */
@Entity
@Table(name = "users")
public class User extends AbstractIdEntity {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void create(UserRepository repo) {
        repo.save(this);
    }
}
