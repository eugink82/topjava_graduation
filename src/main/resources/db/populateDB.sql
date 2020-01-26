DELETE FROM vote;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM restaurant;
DELETE FROM dish;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin'),
  ('Serge85', 'serge85@mail.ru', 'qwerty'),
  ('Nick', 'Nick@rambler.ru', 'pass');

INSERT INTO user_roles (role, user_id)
VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100002),
  ('ROLE_USER', 100003);

INSERT INTO restaurant (name)
VALUES
  ('Тифлисский дворик'),
  ('Обломов');


INSERT INTO dish (date,name,price,restaurant_id)
VALUES
(current_date,'Венский шницель',450.00,100004),
(current_date,'Шанхайские пельмени',570.00,100004),
(current_date,'Борщ украинский',180.00,100004),
(current_date,'Маринованная селедка с овощами',160.00,100005),
(current_date,'Цыплята табака',220.00,100005),
(current_date,'Суп старополтавский',155.00,100005),
(current_date,'Вишневый морс',80.00,100005),
(current_date-1,'Котлета по киевски',90.00,100004),
(current_date-1,'Ципилинай',150.00,100005),
(current_date-1,'Шулюм',115.00,100005),
(current_date-1,'Шашлык по грузински',160.00,100005),
(current_date-1,'Квас',220.00,100005),
(current_date-1,'Вареники с клубникой',155.00,100004),
(current_date-1,'Макароны по флотски',80.00,100004),
(current_date-1,'Семга на пару',80.00,100004);


INSERT INTO vote (user_id, date, restaurant_id)
VALUES
(100000, '2020-01-21', 100004),
(100001, '2020-01-21', 100004),
(100002, '2020-01-21', 100004),
(100003, '2020-01-21', 100005),
(100000, '2020-01-22', 100005),
(100001, '2020-01-22', 100004),
(100002, '2020-01-22', 100005),
(100003, '2020-01-22', 100004),
(100000, '2020-01-23', 100005),
(100001, '2020-01-23', 100005),
(100002, '2020-01-23', 100005),
(100003, '2020-01-23', 100004),
(100001, current_date, 100004);

