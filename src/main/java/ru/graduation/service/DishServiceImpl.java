package ru.graduation.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduation.model.Dish;
import ru.graduation.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.graduation.util.ValidationUtil.*;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository repository;

    public DishServiceImpl(DishRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "restaurant must not be null");
        return repository.save(dish,restaurantId);
    }

    @Override
    public void update(Dish dish, int restaurantId) {
        Assert.notNull(dish, "restaurant must not be null");
        checkNotFoundWithId(repository.save(dish,restaurantId),restaurantId);
    }

    @Override
    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(repository.delete(id,restaurantId),restaurantId);
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return checkNotFoundWithId(repository.get(id,restaurantId),restaurantId);
    }

    @Override
    public List<Dish> getDishesByName(String name) {
        return null;
    }

    @Override
    public List<Dish> getMenu(LocalDate date, int restaurantId) {
        return repository.getMenu(date,restaurantId);
    }
}
