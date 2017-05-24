package de.bettinggame.model;

import javax.persistence.*;
import java.util.Date;

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
