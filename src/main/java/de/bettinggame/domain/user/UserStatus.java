package de.bettinggame.domain.user;

import de.bettinggame.domain.MessageKeyAware;

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
