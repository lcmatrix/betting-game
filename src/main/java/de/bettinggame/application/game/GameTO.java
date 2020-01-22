package de.bettinggame.application.game;

import de.bettinggame.application.team.TeamTO;
import de.bettinggame.domain.Multilingual;
import de.bettinggame.domain.game.Game;

import java.time.OffsetDateTime;

/**
 * Representation of a game.
 */
public class GameTO {
    private String id;
    private OffsetDateTime starttime;
    private Multilingual location;
    private TeamTO homeTeam;
    private TeamTO guestTeam;
    private Integer goalsHomeTeam;
    private Integer goalsGuestTeam;

    public GameTO(Game game) {
        this.id = game.identifier();
        this.starttime = game.getStarttime();
        this.location = game.getLocation().getName();
        this.homeTeam = new TeamTO(game.getHomeTeam());
        this.guestTeam = new TeamTO(game.getGuestTeam());
        this.goalsHomeTeam = game.getGoalsHomeTeam();
        this.goalsGuestTeam = game.getGoalsGuestTeam();
    }

    public String getId() {
        return id;
    }

    public OffsetDateTime getStarttime() {
        return starttime;
    }

    public Multilingual getLocation() {
        return location;
    }

    public TeamTO getHomeTeam() {
        return homeTeam;
    }

    public TeamTO getGuestTeam() {
        return guestTeam;
    }

    public Integer getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public Integer getGoalsGuestTeam() {
        return goalsGuestTeam;
    }
}
