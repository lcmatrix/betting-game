/*
 * Created on 24.12.2017
 *
 * Copyright(c) 1995 - 2017 T-Systems Multimedia Solutions GmbH
 * Riesaer Str. 5, 01129 Dresden
 * All rights reserved.
 */

package de.bettinggame.webobjects.groups;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a group in web ui.
 *
 * @author norman
 */
public class Group {
    /**
     * Groups character.
     */
    private String groupChar;

    /**
     * Teams in this group.
     */
    private List<Team> teams;

    /**
     * No-args constructor.
     */
    public Group() {
        this.teams = new ArrayList<>();
    }

    /**
     * Constructor with {@link de.bettinggame.model.Group} enum as argument.
     *
     * @param group {@link de.bettinggame.model.Group} value
     */
    public Group(de.bettinggame.model.Group group) {
        this(group.name());
    }

    /**
     * Constructor with a string representing a {@link de.bettinggame.model.Group} value.
     *
     * @param groupChar string representing a {@link de.bettinggame.model.Group} value
     */
    public Group(String groupChar) {
        this();
        this.groupChar = groupChar;
    }

    public String getGroupChar() {
        return groupChar;
    }

    /**
     * Add a team to this group.
     *
     * @param team team object
     */
    public void addTeam(Team team) {
        this.teams.add(team);
    }

    public List<Team> getTeams() {
        return teams;
    }
}
