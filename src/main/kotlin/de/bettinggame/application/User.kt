package de.bettinggame.application

import de.bettinggame.domain.UserRepository
import de.bettinggame.domain.User
import de.bettinggame.domain.UserRole
import de.bettinggame.domain.UserStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException
import javax.transaction.Transactional
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Service
class UserService(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder
) {
    private val LOG: Logger = LoggerFactory.getLogger(UserService::class.java)

    @Transactional
    fun registerUser(command: RegisterUserCommand) {
        val newUser = User(userRepository.nextIdentifier(), command.username, command.email, UserRole.USER, UserStatus.PENDING)
        newUser.updatePassword(passwordEncoder.encode(command.password))
        userRepository.save(newUser)
        LOG.info("New user registered [{}]", command.username)
    }

    @Transactional
    fun updateUser(username: String, command: EditUserCommand) {
        val user = userRepository.findByUsername(username)
        if (user != null) {
            user.updateData(
                    command.username,
                    command.email,
                    command.firstname,
                    command.surname,
                    command.role,
                    command.status)
        } else {
            throw EntityNotFoundException()
        }
        LOG.info("Changed user data for user [{}]", username)
    }

    @Transactional
    fun lockUser(username: String) {
        val user = userRepository.findByUsername(username)
        if (user != null) {
            user.lock()
        } else {
            throw EntityNotFoundException()
        }
        LOG.info("Locked user [{}]", username)

    }

    @Transactional
    fun unlockUser(username: String) {
        val user = userRepository.findByUsername(username)
        if (user != null) {
            user.unlock()
        } else {
            throw EntityNotFoundException()
        }
        LOG.info("Unlocked user [{}]", username)
    }

    @Transactional
    fun activateUser(username: String) {
        val user = userRepository.findByUsername(username)
        if (user != null) {
            user.activate()
        } else {
            throw EntityNotFoundException()
        }
        LOG.info("Activate user [{}]", username)
    }
}

data class UserTo(
        val username: String,
        val email: String,
        val firstname: String?,
        val surname: String?,
        val status: UserStatus,
        val role: UserRole
) {
    val actions = mutableListOf(UserAction(AdminUserLink.EDIT, username))

    constructor(user: User) : this(
            user.username,
            user.email,
            user.firstname,
            user.surname,
            user.status,
            user.role
    ) {
        when (status) {
            UserStatus.PENDING -> actions.add(UserAction(AdminUserLink.ACTIVATE, username))
            UserStatus.ACTIVE -> actions.add(UserAction(AdminUserLink.LOCK, username))
            UserStatus.LOCKED -> actions.add(UserAction(AdminUserLink.UNLOCK, username))
        }
    }
}

data class UserAction(val name: String, val link: String, val icon: String) {
    constructor(adminUserLink: AdminUserLink, username: String) : this(
            adminUserLink.linkname,
            String.format(adminUserLink.urlTemplate, username),
            adminUserLink.icon
    )
}

enum class AdminUserLink(val linkname: String, val urlTemplate: String, val icon: String) {
    EDIT("edit", "/admin/user/%s/edit", "/static/img/glyphicons/glyphicons-151-edit.png"),
    ACTIVATE("activate", "/admin/user/%s/activate", "/static/img/glyphicons/glyphicons-153-check.png"),
    LOCK("lock", "/admin/user/%s/lock", "/static/img/glyphicons/glyphicons-204-lock.png"),
    UNLOCK("unlock", "/admin/user/%s/unlock", "/static/img/glyphicons/glyphicons-205-unlock.png")
}

data class EditUserCommand(
        @NotBlank(message = "field.not.blank")
        @Size(max = 50, message = "field.max.50.characters")
        var username: String,

        @NotBlank(message = "field.not.blank")
        @Size(max = 200, message = "field.max.200.characters")
        var email: String,

        @Size(max = 200, message = "field.max.200.characters")
        var firstname: String?,

        @Size(max = 200, message = "field.max.200.characters")
        var surname: String?,

        @NotNull(message = "field.not.blank")
        var status: UserStatus,

        @NotNull(message = "field.not.blank")
        var role: UserRole
) {
    constructor(user: User) : this(
            user.username, user.email, user.firstname, user.surname, user.status, user.role
    )
}