package de.bettinggame.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import de.bettinggame.team.objects.Group;
import de.bettinggame.team.services.TeamService;

/**
 * Controller to show groups.
 *
 * @author norman
 */
@Controller
public class GroupController extends AbstractController {

    @Resource
    private TeamService teamService;

    /**
     * Display groups for tournament.
     *
     * @return {@link ModelAndView} with groups and view name
     */
    @GetMapping("/groups")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("groups/groups");

        Collection<Group> allGroups = teamService.getAllGroupsWithTeams();
        mav.addObject("groups", allGroups);

        if (!mav.getModelMap().containsAttribute("groups")) {
            mav.addObject("groups", new ArrayList<Group>());
        }
        return mav;
    }
}
