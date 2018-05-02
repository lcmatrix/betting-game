package de.bettinggame.adapter;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

import de.bettinggame.ui.Navigation;

/**
 * Abstract base controller.
 */
public abstract class AbstractController {

    /**
     * Returns navigation items
     */
    @ModelAttribute("navigation")
    public Navigation[] getNavigation() {
        boolean authenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        Navigation.getNavigationListForAuthentication(authenticated);
        return Navigation.values();
    }
}
