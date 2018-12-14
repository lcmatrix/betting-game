package de.bettinggame.domain.betting;

import de.bettinggame.domain.AbstractIdentifiableEntity;
import de.bettinggame.domain.Identity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bet")
public class Bet extends AbstractIdentifiableEntity {

    @Column(name = "goals_home_team")
    private int goalsHomeTeam;

    @Column(name = "goals_guest_team")
    private int goalsGuestTeam;

    @Embedded
    private Quota quota;

    @Embedded
    @AttributeOverride(name = "identifier", column = @Column(name = "game_identifier"))
    private Identity gameIdentifier;

    public Bet(final Identity gameIdentifier, final int goalsHomeTeam, final int goalsGuestTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
        this.goalsGuestTeam = goalsGuestTeam;
        this.gameIdentifier = gameIdentifier;
    }

    public int getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public int getGoalsGuestTeam() {
        return goalsGuestTeam;
    }

    public Identity getGameIdentifier() {
        return gameIdentifier;
    }
}
