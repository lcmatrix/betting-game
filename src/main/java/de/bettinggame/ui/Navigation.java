package de.bettinggame.ui;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enum with navigation links.
 */
public enum Navigation {

    LOGIN("navigation.item.login", "/login", false),
    REGISTER("navigation.item.register", "/register/register", false),
    GROUPS("navigation.item.groups", "/groups", false);

    private String messageKey;
    private String url;
    private boolean onlyAuthenticatedVisible;

    Navigation(String messageKey, String url, boolean onlyAuthenticatedVisible) {
        this.messageKey = messageKey;
        this.url = url;
        this.onlyAuthenticatedVisible = onlyAuthenticatedVisible;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public String getUrl() {
        return url;
    }

    public boolean isOnlyAuthenticatedVisible() {
        return onlyAuthenticatedVisible;
    }

    public static List<Navigation> getNavigationListForAuthentication() {
        return Stream.of(Navigation.values())
                .filter(item -> item.onlyAuthenticatedVisible)
                .collect(Collectors.toList());
    }
}
