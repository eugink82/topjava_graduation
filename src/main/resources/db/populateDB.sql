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


INSERT INTO menu (date, restaurant_id)
VALUES
('2019-11-30', 100002),
('2015-11-30', 100003);

INSERT INTO dish (name, price, menu_id)
VALUES
('Венский шницель',450.00, 100004),
('Шанхайские пельмени',570.00, 100004),
('Борщ украинский',180.00, 100004),
('Маринованная селедка с овощами',160.00, 100005),
('Цыплята табака',220.00, 100005),
('Суп старополтавский',155.00, 100005),
('Вишневый морс',80.00, 100005);


INSERT INTO vote (user_id, date, restaurant_id)
VALUES
(100000, '2019-11-30', 100002),
(100001, '2019-11-30', 100003);
