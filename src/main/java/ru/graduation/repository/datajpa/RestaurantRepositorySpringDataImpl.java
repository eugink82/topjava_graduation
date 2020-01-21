package ru.graduation.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RestaurantRepositorySpringDataImpl implements RestaurantRepository {
    private static final Sort SORT_NAME=new Sort(Sort.Direction.ASC,"name");

    @Autowired
    private CrudRestaurantRepository crudRepository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        return crudRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id)!=0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public Restaurant getByName(String name) {
        return crudRepository.getByName(name);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRepository.findAll(SORT_NAME);
    }

    @Override
    public Restaurant getWithDishes(int id) {
        return crudRepository.getWithMenu(id, LocalDate.now());
    }
}
