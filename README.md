  # Система голосования по выбору лучшего ресторана

## Техническое задание

Разработка и реализация REST API с использованием Hibernate / Spring/SpringMVC (или Spring-Boot) без интерфейса.

Постановка задачи:

Создайте систему голосования для принятия решения о том, где пообедать.

 2 типа пользователей: администраторы и обычные пользователи
 Администратор может ввести ресторан и его обеденное меню дня (обычно 2-5 пунктов, просто название блюда и цена)
 Меню меняется каждый день (администраторы делают обновления)
 Пользователи могут голосовать за то, в каком ресторане они хотят пообедать
 На каждого пользователя засчитывается только один голос
 Если пользователь голосует снова в тот же день:
 Если это произойдет до 11: 00, то мы предполагаем, что он передумал.
 Если это после 11: 00, то уже слишком поздно, голосование не может быть изменено.

Каждый ресторан предлагает новое меню каждый день.

В результате предоставьте ссылку на репозиторий github.

Он должен содержать код и README.md с документацией API и командами curl для получения данных для голосования и голосования.

### Объектная модель проекта:
1. Dish - блюдо. Блюда за определенную дату по одному ресторану образуют меню
2. Restaurant - ресторан
3. User - пользователь
4. Role - роль пользователя (ROLE_USER или ROLE_ADMIN)
5. Vote - голос (голос пользователя за ресторан на определенную дату)
6. HistoryMenu - история меню ресторанов
7. HistoryVotes - история голосования.

Для репозитория было использовано 2 технологии: Hibernate и Spring Data Jpa (используется по умолчанию)
Обмен с фронтенд идет через формат JSON.

### Пользователи:
#### 1) Операции со своей учетной записью:

- Просмотреть данные своей учетной:
GET .../profile:


{"id":100002,"name":"Serge85","email":"serge85@mail.ru","password":"qwerty","roles":["ROLE_USER"]}


- Поменять данные своей учетной записи:
PUT  .../profile

В Body:
{
    "id": 100002,
    "name": "Serge86",
    "email": "serge86@mail.ru",
    "password": "osada"
}

- Удалить свою учетную запись:
DELETE .../profile

При удалении пользователя удаляются все голоса по ресторанам связанные с ним.

#### 2) Просмотр ресторанов и их меню (опционально)

- Просмотреть список ресторанов:
GET  .../profile/restaurants

[{"id":100005,"name":"Обломов","dishes":null},{"id":100004,"name":"Тифлисский дворик","dishes":null}]

- Просмотреть список ресторанов с их меню:
GET  .../profile/restaurants?withMenu=true

[{"id":100005,"name":"Обломов","dishes":[{"id":100009,"name":"Маринованная селедка с овощами","date":[2020,1,26],"price":160.00},{"id":100011,"name":"Суп старополтавский","date":[2020,1,26],"price":155.00},{"id":100010,"name":"Цыплята табака","date":[2020,1,26],"price":220.00},{"id":100012,"name":"Вишневый морс","date":[2020,1,26],"price":80.00}]},{"id":100004,"name":"Тифлисский дворик","dishes":[{"id":100007,"name":"Шанхайские пельмени","date":[2020,1,26],"price":570.00},{"id":100006,"name":"Венский шницель","date":[2020,1,26],"price":450.00},{"id":100008,"name":"Борщ украинский","date":[2020,1,26],"price":180.00}]}]

- Получить конкретный ресторан по id:
GET  .../profile/restaurants/{100005}

{"id":100005,"name":"Обломов","dishes":null}

- Получить конкретный ресторан по id с меню:
GET  .../profile/restaurants/{100005}?withMenu=true

{"id":100005,"name":"Обломов","dishes":[{"id":100009,"name":"Маринованная селедка с овощами","date":[2020,1,26],"price":160.00},{"id":100011,"name":"Суп старополтавский","date":[2020,1,26],"price":155.00},{"id":100010,"name":"Цыплята табака","date":[2020,1,26],"price":220.00},{"id":100012,"name":"Вишневый морс","date":[2020,1,26],"price":80.00}]}

- Получить конкретный ресторан по имени:
GET .../profile/restaurants/by?name=Обломов

- Получить конкретный ресторан по имени c меню:
restaurants/by?name=Обломов&withMenu=true

#### 3)Голосование по ресторанам
- Проголосовать за понравившийся ему ресторан:
PUT ...profile/votes/{100004}

{"id":100035,"user":{"id":100002,"name":"Serge85","email":"serge85@mail.ru","password":"qwerty","roles":["ROLE_USER"]},"voteDate":[2020,1,26],"restaurant":{"id":100004,"name":"Тифлисский дворик","dishes":null}}

- Просмотреть свой голос:
GET .../profile/votes

{"email":"serge85@mail.ru","date":[2020,1,26],"name":"Тифлисский дворик"}

Если пользователь не голосовал то при попытке просмотреть свой голос ему вернется пустое значение

Время принятия решения голосованию за ресторан -11 часов дня.

Если пользователь еще в этот день не голосовал и при этом время голосования уже вышло (> 11 часов) то он сможет проголосовать, но переголосовать уже не сможет.

Если пользователь уже голосовал и время голосования вышло то переголосовать уже он не сможет, если же время голосования еще не вышло то он может изменить свое решение сколько угодно раз до контрольной точки (11 часов)

#### 4)Просмотр истории меню и истории голосования:

- Просмотреть историю меню ресторанов на все даты:
GET .../history/menu

[{"name":"Тифлисский дворик","date":[2020,1,26],"dishes":[{"name":"Венский шницель","price":450.00},{"name":"Борщ украинский","price":180.00},{"name":"Шанхайские пельмени","price":570.00}]},{"name":"Обломов","date":[2020,1,26],"dishes":[{"name":"Цыплята табака","price":220.00},{"name":"Суп старополтавский","price":155.00},{"name":"Вишневый морс","price":80.00},{"name":"Маринованная селедка с овощами","price":160.00}]},{"name":"Тифлисский дворик","date":[2020,1,25],"dishes":[{"name":"Семга на пару","price":80.00},{"name":"Котлета по киевски","price":90.00},{"name":"Вареники с клубникой","price":155.00},{"name":"Макароны по флотски","price":80.00}]},{"name":"Обломов","date":[2020,1,25],"dishes":[{"name":"Шашлык по грузински","price":160.00},{"name":"Ципилинай","price":150.00},{"name":"Шулюм","price":115.00},{"name":"Квас","price":220.00}]}]

- Просмотреть историю меню ресторанов на конкретную дату:
GET .../profile/history/menu/2020-01-25

[{"name":"Тифлисский дворик","date":[2020,1,25],"dishes":[{"name":"Семга на пару","price":80.00},{"name":"Котлета по киевски","price":90.00},{"name":"Вареники с клубникой","price":155.00},{"name":"Макароны по флотски","price":80.00}]},{"name":"Обломов","date":[2020,1,25],"dishes":[{"name":"Шашлык по грузински","price":160.00},{"name":"Ципилинай","price":150.00},{"name":"Шулюм","price":115.00},{"name":"Квас","price":220.00}]}]

- Просмотреть историю голосования (количество голосов за тот или иной ресторан) по всем ресторанам на все даты:
GET .../profile/history/votes

[{"id":1,"name":"Тифлисский дворик","date":[2020,1,26],"votes":2},{"id":2,"name":"Обломов","date":[2020,1,23],"votes":3},{"id":3,"name":"Тифлисский дворик","date":[2020,1,23],"votes":1},{"id":4,"name":"Обломов","date":[2020,1,22],"votes":2},{"id":5,"name":"Тифлисский дворик","date":[2020,1,22],"votes":2},{"id":6,"name":"Обломов","date":[2020,1,21],"votes":1},{"id":7,"name":"Тифлисский дворик","date":[2020,1,21],"votes":3}]

- Просмотреть историю голосования на конкретную дату:
[{"id":2,"name":"Обломов","date":[2020,1,23],"votes":3},{"id":3,"name":"Тифлисский дворик","date":[2020,1,23],"votes":1}]
Если пользователь изменяет свое текущее решение по голосованию (отдает свой голос за другой ресторан то история меняется.

### Администраторы:

#### 1)Редактирование пользователей

- Создание пользователя:
POST .../admin/users

В Body:
{ "name": "DenisDK", "email": "DenisDK@rambler.ru",  "password": "yaDeny", "roles": ["ROLE_USER"] }

- Обновление пользователя:
PUT .../admin/users/{100002}

- Удаление пользователя:
DELETE .../admin/users/{100002}

- Получение пользователя:
GET .../admin/users/{100002}

{"id": 100002, "name": "Serge85", "email": "serge85@mail.ru", "password": "qwerty", "roles": ["ROLE_USER"]}

- Получение пользователя по email:
GET  .../admin/users/by?email=serge85@mail.ru

- Получение списка пользователей:
GET /admin/users

[{"id":100001,"name":"Admin","email":"admin@gmail.com","password":"admin","roles":["ROLE_ADMIN","ROLE_USER"]},{"id":100036,"name":"DenisDK","email":"DenisDK@rambler.ru","password":"yaDeny","roles":["ROLE_USER"]},{"id":100003,"name":"Nick","email":"Nick@rambler.ru","password":"pass","roles":["ROLE_USER"]},{"id":100002,"name":"Serge85","email":"serge85@mail.ru","password":"qwerty","roles":["ROLE_USER"]},{"id":100000,"name":"User","email":"user@yandex.ru","password":"password","roles":["ROLE_USER"]}]

#### 2)Редактирование ресторанов

- Создание ресторана:
POST .../admin/restaurants

- Обновление ресторана:
PUT  .../admin/restaurants/{100004}

- Удаление ресторана:
DELETE .../admin/restaurants/{100004}

#### 3)Редактирование меню ресторанов:

- Создание блюда меню:
POST  .../admin/restaurants/100004/dishes
Данные на вход:
{
"name": "Уха карельская",
"price": 160
}
Получаем:
{ "id": 100037, "name": "Уха карельская", "date": [2020,1,26], "price": 160 }


- Обновление блюда:
PUT  .../admin/restaurants/100004/dishes/100037

Данные на вход:
{ "name": "Сом в помидорах","price": 330 }

Получаем:
{ "id": 100037, "name": "Сом в помидорах", "date": [2020,1,26], "price": 330 }


- Удаление блюда:
DELETE .../admin/restaurants/100004/dishes/100037

- Получение меню конкретного ресторана:
GET  .../admin/restaurants/100004/dishes?dateMenu=2020-01-25

[{"id":100013,"name":"Котлета по киевски","date":[2020,1,25],"price":90.00},{"id":100018,"name":"Вареники с клубникой","date":[2020,1,25],"price":155.00},{"id":100019,"name":"Макароны по флотски","date":[2020,1,25],"price":80.00},{"id":100020,"name":"Семга на пару","date":[2020,1,25],"price":80.00}]
