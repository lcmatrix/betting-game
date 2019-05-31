package de.bettinggame.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

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
     * Getter for dbId.
     * @return dbId
     */
    public Integer getDbId() {
        return dbId;
    }

    private void setDbId(Integer dbId) {
        this.dbId = dbId;
    }
}
