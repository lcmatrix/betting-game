package de.bettinggame.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Enum with navigation links.
 */
public enum Navigation {

    INDEX("navigation.item.index", "/", false),
    LOGIN("navigation.item.login", "/login", false),
    LOGOUT("navigation.item.logout", "/logout", true),
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

    public static List<Navigation> getNavigationListForAuthentication(boolean authenticated) {
        List<Navigation> navList = new ArrayList<>();
        for (Navigation nav : Navigation.values()) {

        }
        return navList;
    }
}
