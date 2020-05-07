package de.bettinggame.domain.game;

import de.bettinggame.domain.AbstractIdentifiableEntity;
import de.bettinggame.domain.Team;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by norman on 02.05.2017.
 */
@Entity
@Table(name = "game")
public class Game extends AbstractIdentifiableEntity {
    /**
     * Kick-off date and time.
     */
    @Column
    private OffsetDateTime starttime;

    /**
     * Location of the game.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    /**
     * Level in tournament.
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TournamentLevel level;

    /**
     * Team one ("home team").
     */
    @OneToOne(optional = false)
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeam;

    /**
     * Team two ("guest team").
     */
    @OneToOne(optional = false)
    @JoinColumn(name = "guest_team_id", nullable = false)
    private Team guestTeam;

    @Column(name = "goals_home_team")
    private Integer goalsHomeTeam;

    @Column(name = "goals_guest_team")
    private Integer goalsGuestTeam;

    public Game() {
    }

    /**
     * Constructor with arguments.
     *
     * @param starttime kickoff date and time
     * @param location location auf game
     * @param level level in tournament
     * @param homeTeam first team
     * @param guestTeam second team
     * @param goalsHomeTeam goals for first team
     * @param goalsGuestTeam goals for second team
     */
    public Game(OffsetDateTime starttime, String location, TournamentLevel level, Team homeTeam, Team guestTeam,
                Integer goalsHomeTeam, Integer goalsGuestTeam) {
        this.starttime = starttime;
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
    }

    /**
     * Getter for kickoff date and time.
     *
     * @return kickoff date and time
     */
    public OffsetDateTime getStarttime() {
        return starttime;
    }

    /**
     * Getter for location of game.
     *
     * @return location of game
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Getter for level in tournament.
     *
     * @return level in tournament
     */
    public TournamentLevel getLevel() {
        return level;
    }

    /**
     * Getter for first team.
     *
     * @return first team
     */
    public Team getHomeTeam() {
        return homeTeam;
    }

    /**
     * Getter for second team.
     *
     * @return second team
     */
    public Team getGuestTeam() {
        return guestTeam;
    }

    public Integer getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public Integer getGoalsGuestTeam() {
        return goalsGuestTeam;
    }
}
