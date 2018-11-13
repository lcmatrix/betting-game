package de.bettinggame.domain.repository;

import de.bettinggame.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for {@link User} entity.
 */
public interface UserRepository extends JpaRepository<User, Integer>, IdentifierRepository {

    /**
     * Find a user by its e-mail address
     * @param email e-Mail address
     * @return user
     */
    Optional<User> findByEmail(String email);

    /**
     * Find a user by its username
     * @param username username
     * @return user
     */
    Optional<User> findByUsername(String username);

    /**
     * Find a user by its username or by its e-Mail address.
     *
     * @param username username
     * @param email e-Mail address
     * @return user
     */
    Optional<User> findByUsernameOrEmail(String username, String email);
}
