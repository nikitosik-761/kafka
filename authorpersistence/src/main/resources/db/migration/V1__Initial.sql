DROP TABLE IF EXISTS Authors;

CREATE TABLE Authors (
        id bigint NOT NULL PRIMARY KEY,
        name text,
        age int
);