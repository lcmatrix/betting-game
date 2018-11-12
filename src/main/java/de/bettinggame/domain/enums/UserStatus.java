package de.bettinggame.domain.enums;

import de.bettinggame.domain.support.MessageKeyAware;

public enum UserStatus implements MessageKeyAware {
    ACTIVE("user.status.active"),
    PENDING("user.status.pending"),
    LOCKED("user.status.locked");

    private final String messageKey;

    UserStatus(final String messageKey) {
        this.messageKey = messageKey;
    }

    @Override
    public String messageKey() {
        return messageKey;
    }
}
