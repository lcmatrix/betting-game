package de.bettinggame.application.bet;

import de.bettinggame.domain.betting.Bet;
import de.bettinggame.domain.game.Game;
import de.bettinggame.domain.user.User;
import org.apache.commons.lang3.Validate;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class BetTo {
    private String userTip;
    private String user;
    private String game;

    public BetTo(final Bet bet, final Game game, final User user) {
        Validate.notNull(bet);
        Validate.notNull(game);
        Validate.notNull(user);
        Locale locale = LocaleContextHolder.getLocale();
        userTip = bet.getGoalsHomeTeam() + ":" + bet.getGoalsGuestTeam();
        this.user = user.getUsername();
        this.game = game.getHomeTeam().getName().getValueForLocale(locale) + " vs. "
                + game.getGuestTeam().getName().getValueForLocale(locale);
    }

    public String getUserTip() {
        return userTip;
    }

    public String getUser() {
        return user;
    }

    public String getGame() {
        return game;
    }
}
