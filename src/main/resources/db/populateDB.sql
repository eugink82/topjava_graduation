DELETE FROM vote;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM restaurant;
DELETE FROM dish;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO restaurant (name)
VALUES
  ('Тифлисский дворик'),
  ('Обломов');


INSERT INTO dish (date,name,price,restaurant_id)
VALUES
(current_date,'Венский шницель',450.00,100002),
(current_date,'Шанхайские пельмени',570.00,100002),
(current_date,'Борщ украинский',180.00,100002),
(current_date,'Маринованная селедка с овощами',160.00,100003),
(current_date,'Цыплята табака',220.00,100003),
(current_date,'Суп старополтавский',155.00,100003),
(current_date,'Вишневый морс',80.00,100003),
(current_date+1,'Котлета по киевски',90.00,100002),
(current_date+1,'Ципилинай',150.00,100003),
(current_date+1,'Шулюм',115.00,100003),
(current_date+1,'Шашлык по грузински',160.00,100003),
(current_date+1,'Квас',220.00,100003),
(current_date+1,'Вареники с клубникой',155.00,100002),
(current_date+1,'Макароны по флотски',80.00,100002),
(current_date+1,'Семга на пару',80.00,100002);


INSERT INTO vote (user_id, date, restaurant_id)
VALUES
(100000, '2015-11-30', 100003),
(100001, '2015-11-30', 100002),
(100000, '2015-11-29', 100003),
(100001, '2015-11-29', 100002);
