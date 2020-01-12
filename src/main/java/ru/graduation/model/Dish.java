package ru.graduation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;

@Entity
@Table(name = "dish",uniqueConstraints = {@UniqueConstraint(name="dish_unique_name_idx",columnNames ="name" )})
public class Dish extends AbstractNamedEntity {

    public Dish() {
    }

    public Dish(Integer id, String name) {
        super(id, name);
    }
}
