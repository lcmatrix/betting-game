package de.bettinggame.adapter

import de.bettinggame.application.TeamService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

/**
 * Controller to show groups.
 *
 * @author norman
 */
@Controller
class GroupController(private val teamService: TeamService) : AbstractController {
    /**
     * Display groups for tournament.
     *
     * @return {@link ModelAndView} with groups and view name
     */
    @GetMapping("/groups")
    fun index(): ModelAndView {
        val mav = ModelAndView("groups/groups")
        mav.addObject("groups", teamService.getAllGroupsWithTeams())
        return mav
    }
}