package de.bettinggame.adapter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import de.bettinggame.domain.User;
import de.bettinggame.domain.repository.UserRepository;
import de.bettinggame.application.registration.RegisterUser;

/**
 * Register controller to create an account.
 */
@Controller
public class RegisterController implements AbstractController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String showRegisterForm() {
        return "registration/register";
    }

    @PostMapping("/registration")
    public String postRegisterForm(@Valid RegisterUser registerUserForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration/register";
        }
        registerUserForm.setPassword(passwordEncoder.encode(registerUserForm.getPassword()));
        User user = registerUserForm.createUser();
        user.create(userRepository);
        return "redirect:/registration/confirmation";
    }

    @GetMapping("/registration/confirmation")
    public String confirm() {
        return "registration/registerConfirmation";
    }

    @ModelAttribute
    public RegisterUser createRegisterUser() {
        return new RegisterUser();
    }
}
