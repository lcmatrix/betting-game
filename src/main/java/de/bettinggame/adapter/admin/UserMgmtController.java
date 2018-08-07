package de.bettinggame.adapter.admin;

import de.bettinggame.adapter.AbstractController;
import de.bettinggame.application.admin.EditUserCommand;
import de.bettinggame.application.admin.UserService;
import de.bettinggame.application.admin.UserTo;
import de.bettinggame.domain.enums.UserRole;
import de.bettinggame.domain.enums.UserStatus;
import de.bettinggame.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Controller for user management available only for administration users.
 */
@Controller()
public class UserMgmtController implements AbstractController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * List all users.
     */
    @GetMapping("/admin/user")
    public ModelAndView getAllUsers() {
        List<UserTo> allUsers = userRepository.findAll().stream().map(UserTo::new).collect(toList());
        ModelAndView mav = new ModelAndView("admin/user/user-list");
        mav.addObject("allUsers", allUsers);
        return mav;
    }

    /**
     * Edit a specific user.
     *
     * @param userId username
     */
    @GetMapping("/admin/user/{userId}/edit")
    public ModelAndView editUser(@PathVariable String userId) {
        Optional<EditUserCommand> user = userRepository.findByUsername(userId).map(EditUserCommand::new);
        ModelAndView mav = new ModelAndView("admin/user/user-edit");
        user.ifPresent(u -> {
            mav.addObject(u);
            mav.addObject("userkey", u.getUsername());
        });
        mav.addObject("userroles", UserRole.values());
        mav.addObject("userstatus", UserStatus.values());
        return mav;
    }

    @PostMapping("/admin/user/{userId}/edit")
    public String saveUser(@PathVariable String userId, @Valid EditUserCommand user, BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("userroles", UserRole.values());
            model.addAttribute("userstatus", UserStatus.values());
            model.addAttribute("userkey", userId);
            return "admin/user/user-edit";
        }
        userService.updateUser(user, userId);
        return "redirect:/admin/user?confirm";
    }
}
