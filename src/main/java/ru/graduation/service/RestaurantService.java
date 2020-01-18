package ru.graduation.service;

import ru.graduation.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    void update(Restaurant restaurant);

    Restaurant get(int id);

    Restaurant getByName(String name);

    void delete(int id);

    List<Restaurant> getAll();

    Restaurant getWithDishes(int id);

}
