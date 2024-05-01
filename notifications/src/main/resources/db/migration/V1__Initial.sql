DROP TABLE IF EXISTS Notifications;
CREATE SEQUENCE notifications_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE Notifications (
        id bigint primary key,
        timestamp timestamp NOT NULL ,
        message TEXT,
        service TEXT
);