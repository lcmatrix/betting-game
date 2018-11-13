package de.bettinggame.domain;

import org.apache.commons.lang3.Validate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * Abstract class for entities. Provides dbId attribute.
 */
@MappedSuperclass
public abstract class AbstractIdEntity {

    /**
     * Id column for Hibernate.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer dbId;

    /**
     * Identifier for an entity. It's an UUID.
     */
    private String identifier;

    /**
     * Getter for dbId.
     * @return dbId
     */
    public Integer getDbId() {
        return dbId;
    }

    private void setDbId(Integer dbId) {
        this.dbId = dbId;
    }

    /**
     * Get entity identifier which is an UUID.
     * @return entity identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    protected void setIdentifier(String identifier) {
        Validate.notBlank(identifier, "Not a valid entity identifier");
        this.identifier = identifier;
    }
}
