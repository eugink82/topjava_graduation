package ru.graduation.to;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class HistoryMenuTo {

    private String name;

    private LocalDate date;

    private List<DishTo> dishes;

    public HistoryMenuTo(String name, LocalDate date, List<DishTo> dishes) {
        this.name = name;
        this.date = date;
        this.dishes = dishes;
    }
}
