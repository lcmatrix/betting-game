package de.bettinggame.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by norman on 02.05.2017.
 */
@Entity
@Table(name = "matches")
public class Match extends AbstractIdEntity{
    /**
     * Kick-off date and time.
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date starttime;

    /**
     * Location of the match.
     */
    @Column(nullable = false)
    private String location;

    /**
     * Level in tournament.
     */
    @Embedded
    @Enumerated(EnumType.STRING)
    private TournamentLevel level;

    /**
     * Team one ("home team").
     */
    @OneToOne(optional = false)
    @JoinColumn(name = "team_a_id", nullable = false)
    private Team teamA;

    /**
     * Team two ("guest team").
     */
    @OneToOne(optional = false)
    @JoinColumn(name = "team_b_id", nullable = false)
    private Team teamB;

    public Match() {
    }

    /**
     * Constructor with arguments.
     *
     * @param starttime kickoff date and time
     * @param location location auf match
     * @param level level in tournament
     * @param teamA first team
     * @param teamB second team
     */
    public Match(Date starttime, String location, TournamentLevel level, Team teamA, Team teamB) {
        this.starttime = starttime;
        this.teamA = teamA;
        this.teamB = teamB;
    }

    /**
     * Getter for kickoff date and time.
     *
     * @return kickoff date and time
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * Getter for location of match.
     *
     * @return location of match
     */
    public String getLocation() {
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
    public Team getTeamA() {
        return teamA;
    }

    /**
     * Getter for second team.
     *
     * @return second team
     */
    public Team getTeamB() {
        return teamB;
    }
}
