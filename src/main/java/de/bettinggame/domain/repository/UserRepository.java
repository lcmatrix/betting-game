package de.bettinggame.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.bettinggame.domain.User;

import java.util.Optional;

/**
 * Repository for {@link User} entity.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Find a user by its e-mail address
     * @param email e-Mail address
     * @return user
     */
    User findByEmail(String email);

    /**
     * Find a user by its username
     * @param username username
     * @return user
     */
    Optional<User> findByUsername(String username);
}
