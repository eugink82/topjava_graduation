package ru.graduation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import ru.graduation.util.DateUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "dish",uniqueConstraints = {@UniqueConstraint(name="dish_unique_date_name_restaurant_idx",columnNames ={"date","name","restaurant_id"})})
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
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(String name, BigDecimal price) {
        this(null,name,price);
    }

    public Dish(Integer id, String name, BigDecimal price) {
        super(id, name);
        this.date = DateUtil.CURR_DATE;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id="+id+
                ", date=" + date +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
