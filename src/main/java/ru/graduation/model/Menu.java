package ru.graduation.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Menu {

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable = false)
    @Id
    private Restaurant restaurant;

    @Getter
    @Setter
    @Id
    @NotNull
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="dish_id", nullable = false)
    @Id
    private Dish dish;

    @Getter
    @Setter
    @NotNull
    @Range(min=5, max=1500)
    private BigDecimal price;

    public Menu() {
    }

    public Menu(Restaurant restaurant, LocalDate date, Dish dish, BigDecimal price) {
        this.restaurant = restaurant;
        this.date = date;
        this.dish = dish;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "restaurant=" + restaurant +
                ", date=" + date +
                ", dish=" + dish +
                ", price=" + price +
                '}';
    }
}
