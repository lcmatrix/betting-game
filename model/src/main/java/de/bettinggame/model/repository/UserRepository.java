package de.bettinggame.model.repository;

import org.springframework.data.repository.CrudRepository;

import de.bettinggame.model.User;

/**
 * Repository for {@link User} entity.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * Find a user by its e-mail address
     * @param email e-Mail address
     * @return User
     */
    User findByEmail(String email);
}
