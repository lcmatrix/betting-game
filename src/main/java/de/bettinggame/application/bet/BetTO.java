package de.bettinggame.application.bet;

import de.bettinggame.domain.betting.Bet;
import de.bettinggame.domain.user.User;
import org.apache.commons.lang3.Validate;

public class BetTO {
    private String userTip;
    private String user;

    BetTO(final Bet bet, final User user) {
        Validate.notNull(bet);
        Validate.notNull(user);
        userTip = bet.getGoalsHomeTeam() + ":" + bet.getGoalsGuestTeam();
        this.user = user.getUsername();
    }

    public String getUserTip() {
        return userTip;
    }

    public String getUser() {
        return user;
    }
}
