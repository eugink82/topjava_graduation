package ru.graduation.repository;

import ru.graduation.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    Restaurant getByName(String name);

    List<Restaurant> getAll();

    Restaurant getWithDishes(int id);

}
