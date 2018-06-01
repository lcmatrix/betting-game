package de.bettinggame.application.team;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a group in web ui.
 *
 * @author norman
 */
public class GroupTO {
    /**
     * Groups character.
     */
    private String groupChar;

    /**
     * Teams in this group.
     */
    private List<TeamTO> teams;

    /**
     * No-args constructor.
     */
    public GroupTO() {
        this.teams = new ArrayList<>();
    }

    /**
     * Constructor with {@link de.bettinggame.domain.Group} enum as argument.
     *
     * @param group {@link de.bettinggame.domain.Group} value
     */
    public GroupTO(de.bettinggame.domain.Group group) {
        this(group.name());
    }

    /**
     * Constructor with a string representing a {@link de.bettinggame.domain.Group} value.
     *
     * @param groupChar string representing a {@link de.bettinggame.domain.Group} value
     */
    public GroupTO(String groupChar) {
        this();
        this.groupChar = groupChar;
    }

    public String getGroupChar() {
        return groupChar;
    }

    /**
     * Add a team to this group.
     *
     * @param teamTO team object
     */
    public void addTeam(TeamTO teamTO) {
        this.teams.add(teamTO);
    }

    public List<TeamTO> getTeams() {
        return teams;
    }
}
