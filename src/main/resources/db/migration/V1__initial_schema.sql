CREATE TABLE user (
    dbId INTEGER NOT NULL AUTO_INCREMENT,
    identifier VARCHAR(100) NOT NULL,
    username VARCHAR(150) NOT NULL,
    password VARCHAR(200) NOT NULL,
    email VARCHAR(255) NOT NULL,
    firstname VARCHAR(255),
    surname VARCHAR(255),
    status VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (dbId),
    CONSTRAINT UNIQUE INDEX idx_user_username (username),
    CONSTRAINT UNIQUE INDEX idx_user_email (email),
    CONSTRAINT UNIQUE INDEX uc_identifier (identifier)
);

CREATE TABLE team (
    dbId INTEGER NOT NULL AUTO_INCREMENT,
    identifier VARCHAR(100) NOT NULL,
    name_de VARCHAR(150),
    name_en VARCHAR(150),
    team_key VARCHAR(3) NOT NULL,
    group_char VARCHAR(1),
    CONSTRAINT pk_team PRIMARY KEY (dbId),
    CONSTRAINT UNIQUE INDEX idx_team_team_key(team_key),
    CONSTRAINT UNIQUE INDEX uc_identifier (identifier)
);

CREATE TABLE location (
    dbId INTEGER NOT NULL AUTO_INCREMENT,
    identifier VARCHAR(100) NOT NULL,
    short_key VARCHAR(20) NOT NULL,
    name_de VARCHAR(255) NOT NULL,
    name_en VARCHAR(255) NOT NULL,
    city_de VARCHAR(255) NOT NULL,
    city_en VARCHAR(255) NOT NULL,
    country_de VARCHAR(255) NOT NULL,
    country_en VARCHAR(255) NOT NULL,
    CONSTRAINT pk_location PRIMARY KEY (dbId),
    CONSTRAINT UNIQUE INDEX idx_short_key(short_key),
    CONSTRAINT UNIQUE INDEX uc_identifier (identifier)
);

CREATE TABLE game (
    dbId INTEGER NOT NULL AUTO_INCREMENT,
    identifier VARCHAR(100) NOT NULL,
    starttime DATETIME NOT NULL,
    location_id INTEGER NOT NULL,
    level VARCHAR(50) NOT NULL,
    home_team_id INTEGER NOT NULL,
    guest_team_id INTEGER NOT NULL,
    goals_home_team INTEGER,
    goals_guest_team INTEGER,
    CONSTRAINT pk_match PRIMARY KEY (dbId),
    CONSTRAINT fk_location FOREIGN KEY (location_id) REFERENCES location(dbId),
    CONSTRAINT fk_team_a FOREIGN KEY (home_team_id) REFERENCES team(dbId),
    CONSTRAINT fk_team_b FOREIGN KEY (guest_team_id) REFERENCES team(dbId),
    CONSTRAINT UNIQUE INDEX uc_identifier (identifier)
);

CREATE TABLE news (
    dbId INTEGER NOT NULL AUTO_INCREMENT,
    identifier VARCHAR(100) NOT NULL,
    headline VARCHAR(255) NOT NULL,
    content VARCHAR(2000) NOT NULL,
    publish_date DATETIME NOT NULL,
    author_username VARCHAR(150) NOT NULL,
    author_id INTEGER,
    CONSTRAINT pk_news PRIMARY KEY (dbId),
    CONSTRAINT fk_user FOREIGN KEY (author_id) REFERENCES user(dbId),
    CONSTRAINT UNIQUE INDEX uc_identifier (identifier)
);


INSERT INTO team (dbId, identifier, name_de, name_en, team_key, group_char) VALUES
  (-1, 'e2ff1e30-36e4-4f53-a57c-38a21dde073d', 'unbekannt', 'unknown', 'xxx', null);
