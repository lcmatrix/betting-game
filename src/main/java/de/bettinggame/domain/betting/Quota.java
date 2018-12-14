package de.bettinggame.domain.betting;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Value-object for a bet quota.
 */
@Embeddable
public class Quota {
    @Column
    private double quota;

    public Quota(final double quota) {
        this.quota = quota;
    }

    public double getQuota() {
        return quota;
    }
}
