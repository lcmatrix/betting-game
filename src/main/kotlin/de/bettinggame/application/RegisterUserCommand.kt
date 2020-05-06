package de.bettinggame.application

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Form object for registration.
 */
class RegisterUserCommand {
    @NotNull
    @Size(min = 1, max = 50, message = "registration.form.username.size")
    var username: String? = null

    @NotNull
    @Size(min = 6, message = "registration.form.password.size")
    var password: String? = null

    @NotNull
    @Size(min = 1, max = 200, message = "registration.form.email.size")
    var email: String? = null
}