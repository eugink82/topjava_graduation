package ru.graduation.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name="history_menu")
public class HistoryMenu extends AbstractNamedEntity {

    @NotNull
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @Column(name = "name_dish")
    private String nameDish;

    private BigDecimal price;

//    @OneToMany
//    private List<Dish> dishes;
}
