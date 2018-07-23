package de.bettinggame.adapter.admin;

import de.bettinggame.adapter.AbstractController;
import de.bettinggame.application.admin.UserTo;
import de.bettinggame.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Controller for user management available only for administration users.
 */
@Controller()
public class UserMgmtController implements AbstractController {

    @Autowired
    private UserRepository userRepository;

    /**
     * List all users.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/user")
    public ModelAndView getAllUsers() {
        List<UserTo> allUsers = userRepository.findAll().stream().map(UserTo::new).collect(toList());
        ModelAndView mav = new ModelAndView("admin/user/user-list");
        mav.addObject("allUsers", allUsers);
        return mav;
    }
}
