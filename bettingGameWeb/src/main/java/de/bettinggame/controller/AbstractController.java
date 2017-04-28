package de.bettinggame.controller;

import de.bettinggame.ui.Navigation;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Abstract base controller.
 */
public abstract class AbstractController {

    /**
     * Returns navigation items
     */
    @ModelAttribute("navigation")
    public Navigation[] getNavigation() {
        return Navigation.values();
    }
}
