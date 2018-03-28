package de.bettinggame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * Created by norman on 02.05.2017.
 */
@Entity
@Table(name = "team")
public class Team extends AbstractIdEntity {

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
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Group groupChar;

    /**
     * Constructor.
     */
    public Team() {
    }

    /**
     * Constructor with all arguments.
     *
     * @param country country
     * @param isocode ISO-3166-alpha-2 code
     * @param groupChar group
     */
    public Team(String country, String isocode, Group groupChar) {
        this.country = country;
        this.isoCode = isocode;
        this.groupChar = groupChar;
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
