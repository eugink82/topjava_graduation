DELETE FROM vote;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM dish;
DELETE FROM menu;
DELETE FROM restaurant;
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

INSERT INTO dish (name)
VALUES
('Венский шницель'),
('Шанхайские пельмени'),
('Борщ украинский'),
('Маринованная селедка с овощами'),
('Цыплята табака'),
('Суп старополтавский'),
('Вишневый морс');


INSERT INTO menu (restaurant_id,date,dish_id,price)
VALUES
(100002,'2019-11-30',100004,450.00),
(100002,'2019-11-30',100005,570.00),
(100002,'2019-11-30',100006,180.00),
(100003,'2019-11-30',100007,160.00),
(100003,'2019-11-30',100005,540.00),
(100003,'2019-11-30',100009,155.00),
(100003,'2019-11-30',100010,80.00),
(100002,'2019-12-01',100006,190.00),
(100002,'2019-12-01',100008,570.00),
(100002,'2019-12-01',100010,180.00),
(100002,'2019-12-01',100007,165.00),
(100003,'2019-12-01',100007,155.00),
(100003,'2019-12-01',100005,540.00),
(100003,'2019-12-01',100009,155.00),
(100003,'2019-12-01',100010,80.00);





INSERT INTO vote (user_id, date, restaurant_id)
VALUES
(100000, '2019-11-30', 100002),
(100001, '2019-11-30', 100003);
