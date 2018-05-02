package de.bettinggame.application.team;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Represents a team in web ui.
 *
 * @author norman
 */
public class Team {
    /**
     * Name of the team.
     */
    private String name;

    /**
     * ISO-3166-1 alpha-2 code.
     */
    private String isocode;

    /**
     * Points for this team.
     */
    private int points;

    /**
     * Goals shot by this team.
     */
    private int goals;

    /**
     * Goals against this team.
     */
    private int goalsAgainst;

    /**
     * No-arg constructor.
     */
    public Team() {

    }

    /**
     * Constructor, taking an domain team.
     *
     * @param team team
     */
    public Team(de.bettinggame.domain.Team team) {
        this.name = team.getCountry();
        this.isocode = team.getIsoCode();
        setLocalisedCountryName();
    }

    /**
     * Constructor, taking all necessary arguments.
     *
     * @param name name/country
     * @param isocode ISO-3166-alpha-2 code
     * @param points points
     * @param goals goals
     * @param goalsAgainst goals against
     */
    public Team(String name, String isocode, int points, int goals, int goalsAgainst) {
        this.name = name;
        this.isocode = isocode;
        this.points = points;
        this.goals = goals;
        this.goalsAgainst = goalsAgainst;
        setLocalisedCountryName();
    }

    public String getFlagIconPath() {
        return isocode + ".png";
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getGoals() {
        return goals;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    /**
     * Try to set the name/country of this team by ISO-code, localised for browser language.
     */
    private void setLocalisedCountryName() {
        Locale displayLocale = LocaleContextHolder.getLocale();
        List<Locale> allLocales = Arrays.asList(Locale.getAvailableLocales());
        List<String> countryNames = allLocales.stream()
                .filter(e -> e.getCountry().equals(isocode))
                .map(e -> e.getDisplayCountry(displayLocale))
                .collect(Collectors.toList());
        if (countryNames.size() == 1) {
            name = countryNames.get(0);
        }
    }
}
