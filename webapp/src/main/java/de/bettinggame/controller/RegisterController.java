/*
 * Created on 04.08.2017
 * 
 * Copyright(c) 1995 - 2017 T-Systems Multimedia Solutions GmbH
 * Riesaer Str. 5, 01129 Dresden
 * All rights reserved.
 */

package de.bettinggame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.bettinggame.model.User;
import de.bettinggame.model.repository.UserRepository;
import de.bettinggame.webobjects.RegisterUser;

import javax.validation.Valid;

/**
 * Register controller to create an account.
 */
@Controller
@RequestMapping("/register")
public class RegisterController extends AbstractController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String forward() {
        return "/register";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "registration/register";
    }

    @PostMapping("/register")
    public String postRegisterForm(@Valid RegisterUser registerUserForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration/register";
        }
        User user = registerUserForm.createUser();
        user.create(userRepository);
        return "redirect:/register/confirmation";
    }

    @GetMapping("/confirmation")
    public String confirm() {
        return "registration/registerConfirmation";
    }

    @ModelAttribute
    public RegisterUser createRegisterUser() {
        return new RegisterUser();
    }
}
