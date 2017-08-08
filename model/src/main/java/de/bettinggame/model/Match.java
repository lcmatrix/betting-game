package de.bettinggame.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date starttime;

    @OneToOne(optional = false)
    @JoinColumn(name = "team_a_id", nullable = false)
    private Team teamA;

    @OneToOne(optional = false)
    @JoinColumn(name = "team_b_id", nullable = false)
    private Team teamB;

    public Match() {
    }

    public Match(Date starttime, Team teamA, Team teamB) {
        this.starttime = starttime;
        this.teamA = teamA;
        this.teamB = teamB;
    }

    public Date getStarttime() {
        return starttime;
    }

    public Team getTeamA() {
        return teamA;
    }

    public Team getTeamB() {
        return teamB;
    }
}
