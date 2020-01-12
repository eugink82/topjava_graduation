package ru.graduation.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Dish extends AbstractNamedEntity {

    @Getter
    @Setter
    private LocalDate date;

    @Getter
    @Setter
    private BigDecimal price;

    @Getter
    @Setter
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(LocalDate date, String name, BigDecimal price, Restaurant restaurant) {
        this(null,date,name,price,restaurant);
    }

    public Dish(Integer id, LocalDate date, String name, BigDecimal price, Restaurant restaurant) {
        super(id, name);
        this.date = date;
        this.price = price;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "date=" + date +
                ", name='" + name +
                ", price=" + price +
                ", restaurant=" + restaurant +'\''+
                '}';
    }
}
