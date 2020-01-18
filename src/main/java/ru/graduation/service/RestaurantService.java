package ru.graduation.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.RestaurantRepository;

import java.util.List;

import static ru.graduation.util.ValidationUtil.*;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository=repository;
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "user must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public Restaurant getByName(String name) {
        return checkNotFound(repository.getByName(name), name);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    public Restaurant getWithDishes(int id) {
        return checkNotFoundWithId(repository.getWithDishes(id),id);
    }
}
