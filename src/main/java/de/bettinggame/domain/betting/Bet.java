package de.bettinggame.domain.betting;

import de.bettinggame.domain.AbstractIdentifiableEntity;
import de.bettinggame.domain.Identity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
    @AttributeOverride(name = "value", column = @Column(name = "quota"))
    private Quota quota;

    @Embedded
    @AttributeOverride(name = "identifier", column = @Column(name = "game_identifier"))
    private Identity gameIdentifier;

    @Embedded
    @AttributeOverride(name = "identifier", column = @Column(name = "user_identifier"))
    private Identity userIdentifier;

    public Bet(final Identity gameIdentifier, final Identity userIdentifier, final int goalsHomeTeam, final int goalsGuestTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
        this.goalsGuestTeam = goalsGuestTeam;
        this.gameIdentifier = gameIdentifier;
        this.userIdentifier = userIdentifier;
    }

    protected Bet() {

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

    public Quota getQuota() {
        return quota;
    }

    public Identity getUserIdentifier() {
        return userIdentifier;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17,37)
                .append(goalsHomeTeam).append(goalsGuestTeam).append(quota)
                .append(gameIdentifier).append(userIdentifier).build();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Bet other = (Bet) obj;
        return new EqualsBuilder()
                .append(goalsGuestTeam, other.getGoalsHomeTeam())
                .append(goalsHomeTeam, other.getGoalsHomeTeam())
                .append(quota, other.getQuota())
                .append(gameIdentifier, other.getGameIdentifier())
                .append(userIdentifier, other.getUserIdentifier())
                .build();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
