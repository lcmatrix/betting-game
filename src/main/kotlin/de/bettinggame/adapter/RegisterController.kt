package de.bettinggame.adapter

import de.bettinggame.application.RegisterUserCommand
import de.bettinggame.application.admin.UserService
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

/**
 * Register controller to create an account.
 */
@Controller
class RegisterController(private val userService: UserService) : AbstractController {
    @GetMapping("/registration")
    fun showRegistrationForm() = "registration/register"

    @GetMapping("/registration/confirmation")
    fun confirm() = "registration/registerConfirmation"

    @ModelAttribute
    fun createRegisterUserCommand() = RegisterUserCommand()

    @PostMapping("/registration")
    fun postRegistration(@Valid registerUserCommand: RegisterUserCommand, result: BindingResult): String {
        if (result.hasErrors()) {
            return "registration/register"
        }
        userService.registerUser(registerUserCommand)
        return "redirect:/registration/confirmation"
    }
}