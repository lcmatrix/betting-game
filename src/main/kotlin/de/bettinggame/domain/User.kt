package de.bettinggame.domain

import de.bettinggame.domain.repository.IdentifierRepository
import de.bettinggame.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Repository for {@link User} entity.
 */
interface UserRepository : JpaRepository<User, Int>, IdentifierRepository {
    /**
     * Find a user by its e-mail address
     * @param email e-Mail address
     * @return user
     */
    fun findByEmail(email: String): User?

    /**
     * Find a user by its username
     * @param username username
     * @return user
     */
    fun findByUsername(username: String): User?

    /**
     * Find a user by its username or by its e-Mail address.
     *
     * @param username username
     * @param email e-Mail address
     * @return user
     */
    fun findByUsernameOrEmail(username: String, email: String): User?
}