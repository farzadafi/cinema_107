CREATE TABLE IF NOT EXISTS admin
(
    admin_id   SERIAL PRIMARY KEY,
    first_name VARCHAR(50)        NOT NULL,
    last_name  VARCHAR(50)        NOT NULL,
    username   VARCHAR(50) UNIQUE NOT NULL,
    password   VARCHAR(50)        NOT NULL
);

CREATE TABLE IF NOT EXISTS cinema
(
    cinema_id     SERIAL PRIMARY KEY,
    cinema_name   VARCHAR(50)        NOT NULL,
    cinema_number VARCHAR(50)        NOT NULL,
    username      VARCHAR(50) UNIQUE NOT NULL,
    password      VARCHAR(50)        NOT NULL,
    confirm       VARCHAR(50)        NOT NULL
);

CREATE TABLE IF NOT EXISTS ticket
(
    ticket_id     SERIAL PRIMARY KEY,
    cinema_id     INTEGER,
    FOREIGN KEY (cinema_id) REFERENCES cinema (cinema_id),
    film_name     VARCHAR(50) NOT NULL,
    date_time     DATE        NOT NULL,
    clock         VARCHAR(50) NOT NULL,
    number_ticket INTEGER     NOT NULL,
    price         INTEGER     NOT NULL,
    number_buy    INTEGER     NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    user_id    SERIAL PRIMARY KEY,
    first_name VARCHAR(50)        NOT NULL,
    last_name  VARCHAR(50)        NOT NULL,
    username   VARCHAR(50) UNIQUE NOT NULL,
    email      VARCHAR(50)        NOT NULL,
    password   VARCHAR(50)        NOT NULL
);

CREATE TABLE IF NOT EXISTS basket
(
    basket_id     SERIAL PRIMARY KEY,
    ticket_id     INTEGER,
    FOREIGN KEY (ticket_id) REFERENCES ticket (ticket_id),
    user_id       INTEGER        NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    film_name     VARCHAR(50)    NOT NULL,
    ticket_number INTEGER UNIQUE NOT NULL,
    price_all     INTEGER        NOT NULL
);



