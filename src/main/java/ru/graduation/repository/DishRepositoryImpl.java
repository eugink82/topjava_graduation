package ru.graduation.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Dish;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DishRepositoryImpl implements DishRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Dish save(Dish dish) {
        if(dish.isNew()){
            em.persist(dish);
            return dish;
        }
        return em.merge(dish);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        Query query=em.createQuery("DELETE FROM Dish d WHERE d.id=:id").setParameter("id",id);
        return query.executeUpdate()!=0;
    }

    @Override
    public Dish get(int id) {
        List<Dish> dishes=em.createQuery("SELECT d FROM Dish d WHERE d.id=:id")
                .setParameter("id",id).getResultList();
        return DataAccessUtils.singleResult(dishes);
    }

    @Override
    public List<Dish> getDishesByName(String name) {
        return em.createQuery("SELECT d FROM Dish d WHERE d.name=:name")
                .setParameter("name",name)
                .getResultList();
    }

    @Override
    public List<Dish> getMenu(LocalDate date, int restaurant_id) {
        return em.createQuery("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurant_id AND d.date=:date")
                .setParameter("restaurant_id",restaurant_id)
                .setParameter("date",date)
                .getResultList();
    }
}
