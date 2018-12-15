package de.bettinggame.domain.betting;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Value-object for a bet quota.
 */
@Embeddable
public class Quota {
    @Column
    private double value;

    public Quota(final double value) {
        this.value = value;
    }

    protected Quota() {

    }

    public double getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(value).build();
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).build();
    }
}
