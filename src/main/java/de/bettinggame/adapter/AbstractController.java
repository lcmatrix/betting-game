package de.bettinggame.adapter;

import org.springframework.web.bind.annotation.ModelAttribute;

import de.bettinggame.ui.Navigation;

/**
 * Abstract base controller.
 */
public interface AbstractController {

    /**
     * Returns navigation items
     */
    @ModelAttribute("navigation")
    default Navigation[] getNavigation() {
        Navigation.getNavigationListForAuthentication();
        return Navigation.values();
    }
}
