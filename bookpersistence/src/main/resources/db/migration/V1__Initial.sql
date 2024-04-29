DROP TABLE IF EXISTS Books;

CREATE TABLE Books (
                       isbn text NOT NULL PRIMARY KEY ,
                       title text,
                       author_id bigint
);