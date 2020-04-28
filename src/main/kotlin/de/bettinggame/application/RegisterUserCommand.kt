package de.bettinggame.application

import de.bettinggame.domain.user.User
import de.bettinggame.domain.user.UserRole
import de.bettinggame.domain.user.UserStatus
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Form object for registration.
 */
class RegisterUserCommand(
        @NotNull
        @Size(min = 1, max = 50, message = "registration.form.username.size")
        var username: String,
        @NotNull
        @Size(min = 6, message = "registration.form.password.size")
        var password: String,
        @NotNull
        @Size(min = 1, max = 200, message = "registration.form.email.size")
        var email: String
) {
        constructor() {}

        fun createUser(identifier: String) = User(identifier, username, email, UserStatus.PENDING, UserRole.USER)
}