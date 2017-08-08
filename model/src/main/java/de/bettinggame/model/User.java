package de.bettinggame.model;

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
    private String email;

    public User() {
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
