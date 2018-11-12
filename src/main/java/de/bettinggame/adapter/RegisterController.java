package de.bettinggame.adapter;

import javax.validation.Valid;

import de.bettinggame.application.admin.UserService;
import de.bettinggame.application.registration.RegisterUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Register controller to create an account.
 */
@Controller
public class RegisterController implements AbstractController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String showRegisterForm() {
        return "registration/register";
    }

    @PostMapping("/registration")
    public String postRegisterForm(@Valid RegisterUserCommand registerUserCommand, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration/register";
        }
        userService.registerUser(registerUserCommand);
        return "redirect:/registration/confirmation";
    }

    @GetMapping("/registration/confirmation")
    public String confirm() {
        return "registration/registerConfirmation";
    }

    @ModelAttribute
    public RegisterUserCommand createRegisterUser() {
        return new RegisterUserCommand();
    }
}
