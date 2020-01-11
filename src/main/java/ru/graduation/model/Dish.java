package ru.graduation.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class Dish extends AbstractNamedEntity {

    public Dish() {
    }

    public Dish(Integer id, String name) {
        super(id, name);
    }
}
