package ru.graduation.service;

import ru.graduation.model.Restaurant;
import ru.graduation.model.User;

import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    void update(Restaurant restaurant, int id);

    Restaurant get(int id);

    Restaurant getByName(String name);

    void delete(int id);

    List<Restaurant> getAll();

    Restaurant getWithDishes(int id);

    Restaurant getWithVotes(int id);

}
