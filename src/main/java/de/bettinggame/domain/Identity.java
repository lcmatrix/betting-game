package de.bettinggame.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Value object {@code Identity}. Encapsulates the UUID identifier for entities.
 */
@Embeddable
public class Identity {
    @Column
    private String identifier;

    public Identity(String identifier) {

    }

    public String getIdentifier() {
        return identifier;
    }
}
