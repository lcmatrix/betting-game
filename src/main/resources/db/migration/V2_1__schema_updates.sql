CREATE TABLE bet (
    id INTEGER NOT NULL AUTO_INCREMENT,
    identifier VARCHAR(100) NOT NULL,
    goals_home_team INTEGER NOT NULL,
    goals_guest_team INTEGER NOT NULL,
    quota DOUBLE,
    game_identifier VARCHAR(100) NOT NULL,
    user_identifier VARCHAR(100) NOT NULL,
    CONSTRAINT pk_bet PRIMARY KEY (id),
    INDEX idx_bet_game_identifier (game_identifier),
    INDEX idx_bet_user_identifier (user_identifier),
    INDEX idx_bet_goals_game (goals_home_team, goals_guest_team, game_identifier)
);
