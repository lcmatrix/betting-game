package de.bettinggame.domain;

/**
 * Enumeration for tournament level.
 *
 * @author norman
 */
public enum TournamentLevel {
    PRELIMINARY("tournament.level.preliminary"),
    EIGTH_FINAL("tournament.level.eigth_final"),
    QUARTER_FINAL("tournament.level.quarter_final"),
    SEMI_FINAL("tournament.level.semi_final"),
    SMALL_FINAL("tournament.level.small_final"),
    FINAL("tournament.level.final");

    private String messageKey;

    TournamentLevel(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getMessageKey() {
        return messageKey;
    }
}
