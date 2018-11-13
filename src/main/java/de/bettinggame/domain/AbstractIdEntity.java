package de.bettinggame.domain;

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
    @Column(nullable = false)
    private Integer dbId;

    /**
     * Identifier for an entity.
     */
    private UUID identifier;

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
     *
     * @return entity identifier
     */
    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }
}
