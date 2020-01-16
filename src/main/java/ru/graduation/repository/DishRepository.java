package ru.graduation.repository;

import ru.graduation.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository {

    Dish save(Dish dish, int restaurantId);

    boolean delete(int id, int restaurantId);

    Dish get(int id, int restaurantId);

    List<Dish> getDishesByName(String name);

    List<Dish> getMenu(LocalDate date, int restaurantId);
}
