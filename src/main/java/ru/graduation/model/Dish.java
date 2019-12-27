package ru.graduation.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class Dish extends AbstractNamedEntity {

    @Getter
    @Setter
    private BigDecimal price;

    @Getter
    @Setter
    private Menu menu;

    public Dish(Integer id, String name, BigDecimal price) {
        super(id, name);
        this.price = price;
    }
}
