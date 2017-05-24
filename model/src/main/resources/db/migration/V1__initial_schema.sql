CREATE TABLE users (
    id INTEGER NOT NULL PRIMARY KEY,
    username VARCHAR(150) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE teams (
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(150) NOT NULL
);

CREATE TABLE matches (
    id INTEGER NOT NULL PRIMARY KEY,
    starttime DATETIME NOT NULL,
    team_a_id INTEGER NOT NULL,
    team_b_id INTEGER NOT NULL,
    CONSTRAINT fk_team_a FOREIGN KEY (team_a_id) REFERENCES teams(id),
    CONSTRAINT fk_team_b FOREIGN KEY (team_b_id) REFERENCES teams(id)
);