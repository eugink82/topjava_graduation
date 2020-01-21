package ru.graduation.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Dish;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.DishRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

//@Repository
@Transactional(readOnly = true)
public class DishRepositoryImpl implements DishRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        if(!dish.isNew() && get(dish.getId(),restaurantId)==null){
            return null;
        }
        dish.setRestaurant(em.getReference(Restaurant.class,restaurantId));
        if(dish.isNew()){
            em.persist(dish);
            return dish;
        }
        return em.merge(dish);
    }

    @Override
    @Transactional
    public boolean delete(int id, int restaurantId) {
        Query query=em.createQuery("DELETE FROM Dish d WHERE d.id=:id and d.restaurant.id=:restaurantId")
                .setParameter("id",id)
                .setParameter("restaurantId",restaurantId);
        return query.executeUpdate()!=0;
    }

    @Override
    public Dish get(int id, int restaurantId) {
        List<Dish> dishes=em.createQuery("SELECT d FROM Dish d WHERE d.id=:id and d.restaurant.id=:restaurantId",Dish.class)
                .setParameter("id",id)
                .setParameter("restaurantId",restaurantId).getResultList();
        return DataAccessUtils.singleResult(dishes);
    }

    @Override
    public List<Dish> getDishesByName(String name) {
        return em.createQuery("SELECT d FROM Dish d WHERE d.name=:name",Dish.class)
                .setParameter("name",name)
                .getResultList();
    }

    @Override
    public List<Dish> getMenu(LocalDate date, int restaurantId) {
        return em.createQuery("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurant_id AND d.date=:date order by d.id",Dish.class)
                .setParameter("restaurant_id",restaurantId)
                .setParameter("date",date)
                .getResultList();
    }
}
