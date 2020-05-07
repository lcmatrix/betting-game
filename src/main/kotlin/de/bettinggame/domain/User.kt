package de.bettinggame.domain

import de.bettinggame.domain.repository.IdentifierRepository
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

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
    fun findByUsernameOrEmail(username: String?, email: String?): User?
}

@Entity
class User(identifier: String,
        @NotNull
        @Size(max = 50, message = "field.max.50.characters")
        @Column(nullable = false)
        var username: String,

        @NotNull
        @Column(nullable = false)
        var password: String,

        @NotNull
        @Size(max = 200, message = "field.max.200.characters")
        @Column(nullable = false)
        var email: String,

        @Size(max = 200, message = "field.max.200.characters")
        var firstname: String?,

        @Size(max = 200, message = "field.max.200.characters")
        var surname: String?,

        @NotNull
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        var role: UserRole,

        @NotNull
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        var status: UserStatus
) : AbstractIdentifiableEntity(identifier) {
    val fullname: String?
        get() {
            if (firstname != null && surname != null) {
                return "$firstname $surname"
            } else {
                return null
            }
        }

    constructor(
            identifier: String,
            username: String?,
            email: String?,
            role: UserRole,
            status: UserStatus
    ) : this(identifier, username as String, "", email as String, null, null, role, status)

    fun updateData(
            username: String,
            email: String,
            firstname: String?,
            surname: String?,
            role: UserRole,
            status: UserStatus
    ) {
        this.username = username
        this.email = email
        this.firstname = firstname
        this.surname = surname
        this.role = role
        this.status = status
    }

    fun updatePassword(encodedPassword: String) {
        password = encodedPassword
    }

    fun lock() {
        status = UserStatus.LOCKED
    }

    fun unlock() {
        status = UserStatus.ACTIVE
    }

    fun activate() {
        status = UserStatus.ACTIVE
    }
}

enum class UserRole(override val messageKey: String) : MessageKeyAware {
    USER("user.role.user"),
    ADMIN("user.role.admin")
}

enum class UserStatus(override val messageKey: String) : MessageKeyAware {
    ACTIVE("user.status.active"),
    PENDING("user.status.pending"),
    LOCKED("user.status.locked")
}