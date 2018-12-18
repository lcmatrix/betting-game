package de.bettinggame.domain.team;

import de.bettinggame.domain.AbstractIdentifiableEntity;
import de.bettinggame.domain.Multilingual;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * Entity team.
 */
@Entity
@Table(name = "team", uniqueConstraints =
    @UniqueConstraint(columnNames = "team_key", name = "team_key_unique"))
public class Team extends AbstractIdentifiableEntity {

    /**
     * Team name or country if international.
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "de", column = @Column(name = "name_de")),
            @AttributeOverride(name = "en", column = @Column(name = "name_en"))
    })
    private Multilingual name;

    /**
     * Business key for team. Could be ISO Code for national teams.
     */
    @NotNull
    @Column(name = "team_key", nullable = false)
    private String teamKey;

    /**
     * Group.
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Group groupChar;

    /**
     * Constructor.
     */
    protected Team() {
    }

    /**
     * Constructor with all arguments.
     *
     * @param name team name
     * @param teamKey teamKey
     * @param groupChar group
     */
    public Team(Multilingual name,  String teamKey, Group groupChar) {
        this.name = name;
        this.teamKey = teamKey;
        this.groupChar = groupChar;
    }

    /**
     * Getter for name.
     *
     * @return multilingual team name
     */
    public Multilingual getName() {
        return name;
    }

    /**
     * Getter for team key.
     *
     * @return teamKey
     */
    public String getTeamKey() {
        return teamKey;
    }

    /**
     * Getter for groupChar.
     *
     * @return group
     */
    public Group getGroupChar() {
        return groupChar;
    }
}
