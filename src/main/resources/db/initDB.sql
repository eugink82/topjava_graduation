DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS restaurant;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name     VARCHAR NOT NULL,
  email    VARCHAR NOT NULL,
  password VARCHAR NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
  id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR NOT NULL
);

CREATE UNIQUE INDEX restaurant_unique_name_idx
  ON restaurant (name);

CREATE TABLE dish
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name     VARCHAR NOT NULL
);
CREATE UNIQUE INDEX dish_unique_name_idx ON dish (name);

CREATE TABLE menu
(
  restaurant_id INTEGER   NOT NULL,
  date     date NOT NULL,
  dish_id  INTEGER   NOT NULL,
  price    DECIMAL NOT NULL,
  CONSTRAINT menu_idx UNIQUE (restaurant_id, date,dish_id),
  foreign key (dish_id) REFERENCES dish (id) ON DELETE CASCADE,
  foreign key (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);


CREATE TABLE vote
(
  user_id       INTEGER   NOT NULL,
  date     date NOT NULL,
  restaurant_id INTEGER   NOT NULL,
  CONSTRAINT vote_idx UNIQUE (user_id, date),
  foreign key (user_id) REFERENCES users (id) ON DELETE CASCADE,
  foreign key (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);