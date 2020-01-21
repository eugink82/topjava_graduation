package ru.graduation.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.graduation.model.Dish;
import ru.graduation.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DishRepositorySpringData implements DishRepository {

    @Autowired
    private CrudDishRepository crudDishRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    public Dish save(Dish dish, int restaurantId) {
        if(!dish.isNew() && get(dish.getId(),restaurantId)==null){
            return null;
        }
       dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
       //dish.setRestaurant(crudRestaurantRepository.findById(restaurantId).orElse(null));
       return crudDishRepository.save(dish);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudDishRepository.delete(id,restaurantId)!=0;
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return crudDishRepository.findById(id).filter(dish->dish.getRestaurant().getId()==restaurantId).orElse(null);
    }

    @Override
    public List<Dish> getDishesByName(String name) {
        return crudDishRepository.getByName(name);
    }

    @Override
    public List<Dish> getMenu(LocalDate date, int restaurantId) {
        return crudDishRepository.getMenu(date,restaurantId);
    }
}
