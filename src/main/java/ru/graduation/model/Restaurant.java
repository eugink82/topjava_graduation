package ru.graduation.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Set;

@Entity
@Table(name = "restaurant",uniqueConstraints = {@UniqueConstraint(name="restaurant_unique_name_idx",columnNames ="name" )})
public class Restaurant extends AbstractNamedEntity {

//    @Getter
//    private Set<Menu> menus;
//
//    @Getter
//    private Set<Vote> votes;

    public Restaurant() {
    }

    public Restaurant(Restaurant restaurant) {
        super(restaurant.getId(),restaurant.getName());
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}
