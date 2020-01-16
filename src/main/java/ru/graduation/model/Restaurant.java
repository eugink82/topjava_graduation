package ru.graduation.model;

import lombok.Getter;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "restaurant",uniqueConstraints = {@UniqueConstraint(name="restaurant_unique_name_idx",columnNames ="name")})
public class Restaurant extends AbstractNamedEntity {

    @Getter
    @OneToMany(mappedBy = "restaurant")
    private Set<Dish> dishes;

    public Restaurant() {
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.getId(),restaurant.getName(), restaurant.getDishes());
    }

    public Restaurant(Integer id, String name, Set<Dish> dishes) {
       super(id,name);
       setDishes(dishes);
    }

    public Restaurant(String name){
        this(null,name);
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public void setDishes(Set<Dish> dishes){
        this.dishes= CollectionUtils.isEmpty(dishes) ? null : dishes;
    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishes=" + dishes +
                '}';
    }
}
