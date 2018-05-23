package de.bettinggame.adapter;

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
        Navigation.getNavigationListForAuthentication();
        return Navigation.values();
    }
}
