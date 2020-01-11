package ru.graduation.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Menu {

    @Getter
    @Setter
    private Restaurant restaurant;

    @Getter
    @Setter
    private LocalDate date;

    @Getter
    @Setter
    private Dish dish;

    @Getter
    @Setter
    private BigDecimal price;

    public Menu(Restaurant restaurant, LocalDate date, Dish dish, BigDecimal price) {
        this.restaurant = restaurant;
        this.date = date;
        this.dish = dish;
        this.price = price;
    }
}
