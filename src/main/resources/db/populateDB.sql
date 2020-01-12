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
('2019-11-30','Венский шницель',450.00,100002),
('2015-11-30','Шанхайские пельмени',570.00,100002),
('2015-11-30','Борщ украинский',180.00,100002),
('2015-11-30','Маринованная селедка с овощами',160.00,100003),
('2015-11-30','Цыплята табака',220.00,100003),
('2015-11-30','Суп старополтавский',155.00,100003),
('2015-11-30','Вишневый морс',80.00,100003);


INSERT INTO vote (user_id, date, restaurant_id)
VALUES
(100000, '2019-11-30', 100002),
(100001, '2019-11-30', 100003);
