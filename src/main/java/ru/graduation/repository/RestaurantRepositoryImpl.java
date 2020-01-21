package ru.graduation.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class RestaurantRepositoryImpl  implements RestaurantRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if(restaurant.isNew()){
            em.persist(restaurant);
            return restaurant;
        }
        return em.merge(restaurant);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        Query query=em.createQuery("DELETE FROM Restaurant r WHERE r.id=:id").setParameter("id",id);
        return query.executeUpdate()!=0;
    }

    @Override
    public Restaurant get(int id) {
        return em.find(Restaurant.class,id);
    }

    @Override
    public Restaurant getByName(String name) {
        Query query=em.createQuery("SELECT r FROM Restaurant r WHERE r.name=:name",Restaurant.class);
        List<Restaurant> restaurants=query.setParameter("name",name).getResultList();
        return DataAccessUtils.singleResult(restaurants);
    }

    @Override
    public List<Restaurant> getAll() {
        return em.createQuery("SELECT  r FROM Restaurant r order by r.name",Restaurant.class).getResultList();
    }

    @Override
    public Restaurant getWithDishes(int id) {
        Query query=em.createQuery("SELECT distinct r FROM Restaurant r LEFT JOIN FETCH r.dishes d WHERE r.id=?1 and d.date=?2", Restaurant.class);
        setParameters(query,id,LocalDate.now());
        List<Restaurant> restaurants=query.getResultList();
        return DataAccessUtils.singleResult(restaurants);
    }

    private void setParameters(Query query,int id,LocalDate date){
        query.setParameter(1,id);
        query.setParameter(2, date);
    }
}
