/*
 * Created on 24.12.2017
 *
 * Copyright(c) 1995 - 2017 T-Systems Multimedia Solutions GmbH
 * Riesaer Str. 5, 01129 Dresden
 * All rights reserved.
 */

package de.bettinggame.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import de.bettinggame.model.Team;
import de.bettinggame.model.repository.TeamRepository;
import de.bettinggame.webobjects.groups.Group;

/**
 * Controller to show groups.
 *
 * @author norman
 */
@Controller
public class GroupController extends AbstractController {

    @Resource
    private TeamRepository teamRepository;

    /**
     * Display groups for tournament.
     *
     * @return {@link ModelAndView} with groups and view name
     */
    @GetMapping("/groups")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("groups/groups");
        Map<de.bettinggame.model.Group, Group> groupMap = new TreeMap<>();
        Iterable<Team> teams = teamRepository.findAll();
        for(Team team : teams) {
            Group group = groupMap.get(team.getGroupChar());
            if (group == null) {
                group = new Group(team.getGroupChar());
            }
            group.addTeam(new de.bettinggame.webobjects.groups.Team(team));
            groupMap.put(team.getGroupChar(), group);
        }
        mav.addObject("groups", groupMap.values());

        /*try(Stream<Team> allTeams = teamRepository.findAllTeams()) {
            Map<de.bettinggame.model.Group, List<de.bettinggame.webobjects.groups.Team>> collect =
                    allTeams.collect(Collectors.toMap(
                            Team::getGroupChar,
                            t -> new ArrayList<>(new de.bettinggame.webobjects.groups.Team(t)),
                            (left, right) -> {left.addAll(right); return left; }));
            mav.addObject("groups", collect);
        }*/
        if (!mav.getModelMap().containsAttribute("groups")) {
            mav.addObject("groups", new ArrayList<Group>());
        }
        return mav;
    }
}
