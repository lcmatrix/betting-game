package de.bettinggame.domain;

import org.apache.commons.lang3.Validate;

import javax.persistence.MappedSuperclass;

/**
 * Abstract base class for aggregates. It provides a functional identifier.
 */
@MappedSuperclass
public abstract class AbstractIdentifiableEntity extends AbstractIdEntity {

    /**
     * Identifier for an entity. It's an UUID.
     */
    private Identity identifier;

    /**
     * Get entity identifier which is an UUID.
     * @return entity identifier
     */
    public Identity rawIdentifier() {
        return identifier;
    }

    /**
     * Get entity identifier as String
     * @return identifier as String
     */
    public String identifier() {
        return identifier.identifier();
    }

    protected void setIdentifier(Identity identifier) {
        Validate.notBlank(identifier.identifier(), "Not a valid entity identifier");
        this.identifier = identifier;
    }
}
