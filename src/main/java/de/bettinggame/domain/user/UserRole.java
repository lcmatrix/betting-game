package de.bettinggame.domain.user;

import de.bettinggame.domain.MessageKeyAware;

public enum UserRole implements MessageKeyAware {
    USER("user.role.user"),
    ADMIN("user.role.admin");

    private final String messageKey;

    UserRole(final String messageKey) {
        this.messageKey = messageKey;
    }

    @Override
    public String messageKey() {
        return messageKey;
    }
}
