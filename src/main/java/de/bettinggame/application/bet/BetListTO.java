package de.bettinggame.application.bet;

import de.bettinggame.domain.game.Game;

import java.util.List;
import java.util.Locale;

public class BetListTO {
    private String homeTeam;
    private String guestTeam;
    private String result;
    private List<BetTO> bets;

    BetListTO(Game game, List<BetTO> betTos, Locale locale) {
        homeTeam = game.getHomeTeam().getName().getValueForLocale(locale);
        guestTeam = game.getGuestTeam().getName().getValueForLocale(locale);
        bets = betTos;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getGuestTeam() {
        return guestTeam;
    }

    public String getResult() {
        return result;
    }

    public List<BetTO> getBets() {
        return bets;
    }

    public String getGame() {
        return homeTeam + " vs. " + guestTeam;
    }
}
