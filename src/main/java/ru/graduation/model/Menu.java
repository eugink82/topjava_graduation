package ru.graduation.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class Menu extends AbstractBaseEntity {

    @Getter
    @Setter
    private LocalDate date;

    @Getter
    @Setter
    private Restaurant restaurant;

    public Menu(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }
}
