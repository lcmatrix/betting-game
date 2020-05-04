package de.bettinggame.adapter

import de.bettinggame.domain.repository.NewsRepository
import de.bettinggame.ui.Navigation
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute

interface AbstractController {
    /**
     * Returns navigation items
     */
    @ModelAttribute("nonRestrictedNavigation")
    fun getNonRestrictedNavigation(): List<Navigation> = Navigation.getNonRestrictedNavigation()

    @ModelAttribute("userRestrictedNavigation")
    fun getUserRestrictedNavigation(): List<Navigation> = Navigation.getUserRestrictedNavigation()

    @ModelAttribute("adminRestrictedNavigation")
    fun getAdminRestrictedNavigation(): List<Navigation> = Navigation.getAdminRestrictedNavigation()
}

/**
 * Controller for index/startpage.
 */
@Controller
class MainController(private val newsRepository: NewsRepository) : AbstractController {
    @GetMapping("/login")
    fun login() = "login"

    @GetMapping("/index")
    fun index() = "index"

    @GetMapping("/")
    fun root(model: Model): String {
        val authentication: Authentication? = SecurityContextHolder.getContext().authentication
        if (authentication != null && authentication.isAuthenticated) {
            val news = newsRepository.findAllByOrderByPublishDateDesc()
            model.addAttribute(news)
            return "news/news"
        }
        return "index"
    }
}
