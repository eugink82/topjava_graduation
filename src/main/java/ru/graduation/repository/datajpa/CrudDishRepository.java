package ru.graduation.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Dish;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish,Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=?1 AND d.restaurant.id=?2")
    int delete(int id,int restaurantId);

    @Override
    @Transactional
    Dish save(Dish dish);

    @Query("SELECT d FROM Dish d WHERE d.name=?1")
    List<Dish> getByName(String name);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurant_id AND d.date=:date order by d.id")
    List<Dish> getMenu(@Param("date") LocalDate date,@Param("restaurant_id") int id);


}
