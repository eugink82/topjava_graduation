package ru.graduation.to;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DishTo {

    private String name;

    private BigDecimal price;

    public DishTo(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
