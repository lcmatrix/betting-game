package de.bettinggame.application.bet;

import de.bettinggame.domain.betting.Bet;

import javax.validation.constraints.NotNull;

/**
 * Command object for form input.
 */
public class BetCommand {
    @NotNull
    private Integer goalsHomeTeam;

    @NotNull
    private Integer goalsGuestTeam;

    public BetCommand(Bet bet) {
        this.goalsHomeTeam = bet.getGoalsHomeTeam();
        this.goalsGuestTeam = bet.getGoalsGuestTeam();
    }

    public BetCommand() {
    }

    public Integer getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public void setGoalsHomeTeam(Integer goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    public Integer getGoalsGuestTeam() {
        return goalsGuestTeam;
    }

    public void setGoalsGuestTeam(Integer goalsGuestTeam) {
        this.goalsGuestTeam = goalsGuestTeam;
    }
}
