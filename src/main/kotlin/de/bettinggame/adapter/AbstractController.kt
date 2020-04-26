package de.bettinggame.adapter

import de.bettinggame.ui.Navigation
import org.springframework.web.bind.annotation.ModelAttribute

interface AbstractController {
    /**
     * Returns navigation items
     */
    @JvmDefault
    @ModelAttribute("nonRestrictedNavigation")
    fun getNonRestrictedNavigation(): List<Navigation> = Navigation.getNonRestrictedNavigation()

    @JvmDefault
    @ModelAttribute("userRestrictedNavigation")
    fun getUserRestrictedNavigation(): List<Navigation> = Navigation.getUserRestrictedNavigation()

    @JvmDefault
    @ModelAttribute("adminRestrictedNavigation")
    fun getAdminRestrictedNavigation(): List<Navigation> = Navigation.getAdminRestrictedNavigation()
}
