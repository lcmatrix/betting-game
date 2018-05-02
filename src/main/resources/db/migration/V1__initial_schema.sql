CREATE TABLE user (
    id INTEGER NOT NULL AUTO_INCREMENT,
    username VARCHAR(150) NOT NULL,
    password VARCHAR(200) NOT NULL,
    email VARCHAR(255) NOT NULL,
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

CREATE TABLE `match` (
    id INTEGER NOT NULL AUTO_INCREMENT,
    starttime DATETIME NOT NULL,
    location VARCHAR(200) NOT NULL,
    level VARCHAR(50) NOT NULL,
    team_a_id INTEGER NOT NULL,
    team_b_id INTEGER NOT NULL,
    CONSTRAINT pk_match PRIMARY KEY (id),
    CONSTRAINT fk_team_a FOREIGN KEY (team_a_id) REFERENCES team(id),
    CONSTRAINT fk_team_b FOREIGN KEY (team_b_id) REFERENCES team(id)
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
