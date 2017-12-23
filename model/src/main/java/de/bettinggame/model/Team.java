package de.bettinggame.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * Created by norman on 02.05.2017.
 */
@Entity
@Table(name = "teams")
public class Team extends AbstractIdEntity{

    /**
     * Country.
     */
    @Column(nullable = false)
    private String country;

    /**
     * ISO-3166-1 alpha-2 country code.
     */
    @Column(name = "isocode", nullable = false)
    private String isoCode;

    /**
     * Group.
     */
    @Embedded
    @Enumerated(EnumType.STRING)
    private Group groupChar;

    /**
     * Constructor.
     */
    public Team() {
    }

    /**
     * Getter for country.
     *
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Getter for ISO Code.
     *
     * @return isoCode
     */
    public String getIsoCode() {
        return isoCode;
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
