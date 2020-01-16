package ru.graduation.repository;

import ru.graduation.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository {

    Dish save(Dish dish);

    boolean delete(int id);

    Dish get(int id);

    List<Dish> getDishesByName(String name);

    List<Dish> getMenu(LocalDate date, int restaurant_id);
}
