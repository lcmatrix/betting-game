package de.bettinggame.domain;

import de.bettinggame.domain.support.Multilingual;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Location class.
 */
@Entity
@Table(name = "location")
public class Location extends AbstractIdEntity {

    /**
     * Short unique key.
     */
    @NotNull
    @Column(name = "short_key")
    private String key;

    /**
     * Name of the arena.
     */
    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "de", column = @Column(name = "name_de")),
            @AttributeOverride(name = "en", column = @Column(name = "name_en"))
    })
    private Multilingual name;

    /**
     * City of the arena.
     */
    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "de", column = @Column(name = "city_de")),
            @AttributeOverride(name = "en", column = @Column(name = "city_en"))
    })
    private Multilingual city;

    /**
     * Country of the arena.
     */
    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "de", column = @Column(name = "country_de")),
            @AttributeOverride(name = "en", column = @Column(name = "country_en"))
    })
    private Multilingual country;

    protected Location() {
    }

    public String getKey() {
        return key;
    }

    public Multilingual getName() {
        return name;
    }

    public Multilingual getCity() {
        return city;
    }

    public Multilingual getCountry() {
        return country;
    }
}
