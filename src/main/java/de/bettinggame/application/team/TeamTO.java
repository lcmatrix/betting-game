package de.bettinggame.application.team;


import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Represents a team in web ui.
 *
 * @author norman
 */
public class TeamTO {
    /**
     * Name of the team.
     */
    private String name;

    /**
     * Team key.
     */
    private String teamKey;

    /**
     * Points for this team.
     */
    private int points;

    /**
     * Goals shot by this team.
     */
    private int goals;

    /**
     * Goals against this team.
     */
    private int goalsAgainst;

    /**
     * No-arg constructor.
     */
    public TeamTO() {

    }

    /**
     * Constructor, taking an domain team.
     *
     * @param team team
     */
    public TeamTO(de.bettinggame.domain.Team team) {
        this.name = team.getName().getValueForLocale(LocaleContextHolder.getLocale());
        this.teamKey = team.getTeamKey();
    }

    /**
     * Constructor, taking all necessary arguments.
     *
     * @param name name/country
     * @param teamKey team key
     * @param points points
     * @param goals goals
     * @param goalsAgainst goals against
     */
    public TeamTO(String name, String teamKey, int points, int goals, int goalsAgainst) {
        this.name = name;
        this.teamKey = teamKey;
        this.points = points;
        this.goals = goals;
        this.goalsAgainst = goalsAgainst;
    }

    public String getLogoIconPath() {
        return teamKey + ".png";
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getGoals() {
        return goals;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

}
