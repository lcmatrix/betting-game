package de.bettinggame.model;

import javax.persistence.Column;
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
    @Column
    private String name;

    /**
     * City of the arena.
     */
    @NotNull
    @Column
    private String city;

    /**
     * Country of the arena.
     */
    @NotNull
    @Column
    private String country;

    protected Location() {
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
