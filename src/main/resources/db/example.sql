select r.name,m.date,d.name,m.price
from restaurant r
       inner join menu m on r.id = m.restaurant_id
       inner join dish d on d.id = m.dish_id
where date = '2019-12-01'
order by r.name,date