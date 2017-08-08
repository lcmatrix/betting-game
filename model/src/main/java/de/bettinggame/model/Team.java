package de.bettinggame.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by norman on 02.05.2017.
 */
@Entity
@Table(name = "teams")
public class Team extends AbstractIdEntity{

    public Team() {
    }
}
