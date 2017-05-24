package de.bettinggame.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Abstract class for entities. Provides id attribute.
 */
public abstract class AbstractIdEntity {

    /**
     * The id.
     */
    @Id
    @GeneratedValue
    @Column
    private Integer id;

    /**
     * Getter for id.
     * @return id
     */
    public int getId() {
        return id;
    }
}
