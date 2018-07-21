package de.bettinggame.adapter;

import org.springframework.web.bind.annotation.ModelAttribute;

import de.bettinggame.ui.Navigation;

import java.util.List;

/**
 * Abstract base controller.
 */
public interface AbstractController {

    /**
     * Returns navigation items
     */
    @ModelAttribute("nonRestrictedNavigation")
    default List<Navigation> getNonRestrictedNavigation() {
        return Navigation.getNonRestrictedNavigation();
    }

    @ModelAttribute("userRestrictedNavigation")
    default List<Navigation> getUserRestrictedNavigation() {
        return Navigation.getUserRestrictedNavigation();
    }

    @ModelAttribute("adminRestrictedNavigation")
    default List<Navigation> getAdminRestrictedNavigation() {
        return Navigation.getAdminRestrictedNavigation();
    }
}
