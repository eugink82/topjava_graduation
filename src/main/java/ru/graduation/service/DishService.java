package ru.graduation.service;

import ru.graduation.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishService {

    Dish create(Dish dish, int restaurant_id);

    void update(Dish dish, int restaurant_id);

    void delete(int id, int restaurant_id);

    Dish get(int id,  int restaurant_id);

    List<Dish> getDishesByName(String name);

    List<Dish> getMenu(LocalDate date, int restaurant_id);
}
