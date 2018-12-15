package de.bettinggame.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

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
        this.identifier = identifier;
    }

    protected Identity() {

    }

    public String identifier() {
        return identifier;
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return identifier.equals(obj);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(identifier).build();
    }
}
