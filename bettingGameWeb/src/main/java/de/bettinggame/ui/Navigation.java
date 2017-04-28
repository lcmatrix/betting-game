package de.bettinggame.ui;

/**
 * Enum with navigation links.
 */
public enum Navigation {

    INDEX("navigation.item.index", "/"),
    LOGIN("navigation.item.login", "/login");

    private String messageKey;
    private String url;

    Navigation(String messageKey, String url) {
        this.messageKey = messageKey;
        this.url = url;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public String getUrl() {
        return url;
    }
}
