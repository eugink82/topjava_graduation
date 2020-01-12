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
@Table(name = "dish",uniqueConstraints = {@UniqueConstraint(name="dish_unique_date_name_idx",columnNames ={"date","name"})})
public class Dish extends AbstractNamedEntity {

    @Getter
    @Setter
    @NotNull
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @Getter
    @Setter
    @NotNull
    @Range(min=5, max=1500)
    private BigDecimal price;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable = false)
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
                ", name='" + name + '\'' +
                ", price=" + price +
                ", restaurant=" + restaurant +
                '}';
    }
}
