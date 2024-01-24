--liquibase formatted sql


--changeset artyomdulya:1
CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(64),
    password VARCHAR(64)
);

--changeset artyomdulya:2


CREATE TABLE securities
(
    id            SERIAL PRIMARY KEY,
    id_security   VARCHAR(64),
    sec_id        VARCHAR(64),
    reg_number    VARCHAR(64),
    name          VARCHAR(64),
    emitent_title VARCHAR(64)
);

DROP TABLE securities;
--changeset artyomdulya:3
CREATE TABLE trade_history
(
    id         SERIAL PRIMARY KEY,
    sec_id     VARCHAR(64),
    trade_date TIMESTAMP,
    num_trades DOUBLE PRECISION,
    open       DOUBLE PRECISION,
    close      DOUBLE PRECISION
);

DROP TABLE trade_history;



