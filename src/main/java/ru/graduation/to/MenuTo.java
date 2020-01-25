package ru.graduation.to;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MenuTo {

    private String name_dish;

    private BigDecimal price;

    public MenuTo() {
    }

    public MenuTo(String name_dish, BigDecimal price) {
        this.name_dish = name_dish;
        this.price = price;
    }
}
