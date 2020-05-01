package de.bettinggame.adapter.admin;

import de.bettinggame.adapter.AbstractController;
import de.bettinggame.application.EditUserCommand;
import de.bettinggame.application.UserService;
import de.bettinggame.application.UserTo;
import de.bettinggame.domain.user.UserRole;
import de.bettinggame.domain.user.UserStatus;
import de.bettinggame.domain.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ModelAndView getAllUsers(@ModelAttribute("confirm") String messageKey) {
        List<UserTo> allUsers = userRepository.findAll().stream().map(UserTo::new).collect(toList());
        ModelAndView mav = new ModelAndView("admin/user/user-list");
        mav.addObject("allUsers", allUsers);
        if (StringUtils.isNotBlank(messageKey)) {
            mav.addObject("confirmMessage", "admin.user.action." + messageKey + ".confirm");
        }
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
    public String saveUserChanges(@PathVariable String userId, @Valid EditUserCommand user, BindingResult result,
                                  Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("userroles", UserRole.values());
            model.addAttribute("userstatus", UserStatus.values());
            model.addAttribute("userkey", userId);
            return "admin/user/user-edit";
        }
        userService.updateUser(userId, user);
        redirectAttributes.addFlashAttribute("confirm", "edit");
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/{userId}/{action}")
    public String openUserAction(@PathVariable String userId, @PathVariable String action,  Model model) {
        model.addAttribute("action", action);
        model.addAttribute("userkey", userId);
        switch (action) {
            case "lock":
                model.addAttribute("actionMessage", "admin.user.action.lock.message");
                break;
            case "unlock":
                model.addAttribute("actionMessage", "admin.user.action.lock.message");
                break;
            case "activate":
                model.addAttribute("actionMessage", "admin.user.action.activate.message");
                break;
            default:
                throw new IllegalArgumentException("action not found");
        }
        return "admin/user/user-action";
    }

    @PostMapping("/admin/user/{userId}/lock")
    public String doLock(@PathVariable String userId, RedirectAttributes redirectAttributes) {
        userService.lockUser(userId);
        redirectAttributes.addFlashAttribute("confirm", "lock");
        return "redirect:/admin/user";
    }

    @PostMapping("/admin/user/{userId}/unlock")
    public String doUnlock(@PathVariable String userId, RedirectAttributes redirectAttributes) {
        userService.unlockUser(userId);
        redirectAttributes.addFlashAttribute("confirm", "unlock");
        return "redirect:/admin/user";
    }

    @PostMapping("/admin/user/{userId}/activate")
    public String doActivate(@PathVariable String userId, RedirectAttributes attributes) {
        userService.activateUser(userId);
        attributes.addFlashAttribute("confirm", "activate");
        return "redirect:/admin/user";
    }
}
