package de.bettinggame.ui;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enum with navigation links.
 */
public enum Navigation {

    LOGIN("navigation.item.login", "/login", Restriction.ALL),
    REGISTER("navigation.item.registration", "/registration", Restriction.ALL),
    GROUPS("navigation.item.groups", "/groups", Restriction.ALL),
    MATCH("navigation.item.games", "/game", Restriction.ALL),
    USER_MANAGEMENT("navigation.item.admin.usermanagement", "/admin/user", Restriction.ADMIN);

    private String messageKey;
    private String url;
    private Restriction restriction;

    Navigation(String messageKey, String url, Restriction restriction) {
        this.messageKey = messageKey;
        this.url = url;
        this.restriction = restriction;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public String getUrl() {
        return url;
    }

    public static List<Navigation> getNonRestrictedNavigation() {
        return Stream.of(Navigation.values())
                .filter(nav -> nav.restriction == Restriction.ALL)
                .collect(Collectors.toList());
    }

    public static List<Navigation> getUserRestrictedNavigation() {
        return Stream.of(Navigation.values())
                .filter(nav -> nav.restriction == Restriction.ALL || nav.restriction == Restriction.USER)
                .collect(Collectors.toList());
    }

    public static List<Navigation> getAdminRestrictedNavigation() {
        return Stream.of(Navigation.values())
                .filter(nav -> nav.restriction == Restriction.ADMIN)
                .collect(Collectors.toList());
    }

    private enum Restriction {
        ALL,
        USER,
        ADMIN
    }
}
