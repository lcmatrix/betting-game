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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
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
    public ModelAndView getAllUsers(@RequestParam Map<String, String> params) {
        List<UserTo> allUsers = userRepository.findAll().stream().map(UserTo::new).collect(toList());
        ModelAndView mav = new ModelAndView("admin/user/user-list");
        mav.addObject("allUsers", allUsers);
        if (params.containsKey("confirm")) {
            mav.addObject("confirmMessage", "admin.user.action.confirm." + params.get("confirm"));
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

    @GetMapping("/admin/user/{userId}/lock")
    public String lockUser(@PathVariable String userId, Model model) {
        model.addAttribute("action", "lock");
        model.addAttribute("userkey", userId);
        model.addAttribute("actionMessage", "admin.user.action.lock.message");
        return "admin/user/user-action";
    }

    @PostMapping("/admin/user/{userId}/action/lock")
    public String doLock(@PathVariable String userId) {
        userService.lockUser(userId);
        return "redirect:/admin/user?confirm=lock";
    }

    @GetMapping("/admin/user/{userId}/unlock")
    public String unlockUser(@PathVariable String userId, Model model) {
        model.addAttribute("action", "unlock");
        model.addAttribute("userkey", userId);
        model.addAttribute("actionMessage", "admin.user.action.unlock.message");
        return "admin/user/user-action";
    }

    @PostMapping("/admin/user/{userId}/action/unlock")
    public String doUnlock(@PathVariable String userId) {
        userService.unlockUser(userId);
        return "redirect:/admin/user?confirm=unlock";
    }
}
