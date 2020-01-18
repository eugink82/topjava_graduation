package ru.graduation.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.RestaurantRepository;

import java.util.List;

import static ru.graduation.util.ValidationUtil.*;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository=repository;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "user must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    @Override
    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public Restaurant getByName(String name) {
        return checkNotFound(repository.getByName(name), name);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public Restaurant getWithDishes(int id) {
        return checkNotFoundWithId(repository.getWithDishes(id),id);
    }
}
