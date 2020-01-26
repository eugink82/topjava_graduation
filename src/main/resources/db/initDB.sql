DROP TABLE IF EXISTS user_roles;
Drop VIEW IF EXISTS history_votes;
DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS users;
DROP VIEW IF EXISTS history_menu;
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
  id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  date     date NOT NULL,
  name varchar NOT NULL,
  price DECIMAL,
  restaurant_id INTEGER   NOT NULL,
  foreign key (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX dish_unique_date_name_restaurant_idx
  ON dish (date,name,restaurant_id);

CREATE TABLE vote
(
  id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id       INTEGER   NOT NULL,
  date     date default now() NOT NULL,
  restaurant_id INTEGER   NOT NULL,
  foreign key (user_id) REFERENCES users (id) ON DELETE CASCADE,
  foreign key (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX vote_idx
  ON vote (user_id, date);


create view history_votes as
select  row_number() OVER () AS id,name,date,count(*) as votes from restaurant r inner join vote v on r.id=v.restaurant_id
group by name,date
order by date desc;

create view history_menu as
select  row_number() OVER () AS id,r.name,date,d.name as name_dish,d.price
from restaurant r inner join dish d on r.id=d.restaurant_id
order by date desc,name



