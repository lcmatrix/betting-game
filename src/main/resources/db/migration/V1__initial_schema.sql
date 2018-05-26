CREATE TABLE user (
    id INTEGER NOT NULL AUTO_INCREMENT,
    username VARCHAR(150) NOT NULL,
    password VARCHAR(200) NOT NULL,
    email VARCHAR(255) NOT NULL,
    firstname VARCHAR(255),
    surname VARCHAR(255),
    status VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT UNIQUE INDEX idx_user_email (email)
);

CREATE TABLE team (
    id INTEGER NOT NULL AUTO_INCREMENT,
    country VARCHAR(150) NOT NULL,
    isocode VARCHAR(2) NOT NULL,
    group_char VARCHAR(1) NOT NULL,
    CONSTRAINT pk_team PRIMARY KEY (id)
);

CREATE TABLE location (
    id INTEGER NOT NULL AUTO_INCREMENT,
    short_key VARCHAR(20) NOT NULL,
    name VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    CONSTRAINT pk_location PRIMARY KEY (id),
    CONSTRAINT UNIQUE INDEX idx_short_key(short_key)
);

CREATE TABLE game (
    id INTEGER NOT NULL AUTO_INCREMENT,
    starttime DATETIME NOT NULL,
    location_id INTEGER NOT NULL,
    level VARCHAR(50) NOT NULL,
    home_team_id INTEGER NOT NULL,
    guest_team_id INTEGER NOT NULL,
    goals_home_team INTEGER,
    goals_guest_team INTEGER,
    CONSTRAINT pk_match PRIMARY KEY (id),
    CONSTRAINT fk_location FOREIGN KEY (location_id) REFERENCES location(id),
    CONSTRAINT fk_team_a FOREIGN KEY (home_team_id) REFERENCES team(id),
    CONSTRAINT fk_team_b FOREIGN KEY (guest_team_id) REFERENCES team(id)
);

CREATE TABLE news (
    id INTEGER NOT NULL AUTO_INCREMENT,
    headline VARCHAR(255) NOT NULL,
    content VARCHAR(2000) NOT NULL,
    publish_date DATETIME NOT NULL,
    author_username VARCHAR(150) NOT NULL,
    author_id INTEGER,
    CONSTRAINT pk_news PRIMARY KEY (id),
    CONSTRAINT fk_user FOREIGN KEY (author_id) REFERENCES user(id)
);