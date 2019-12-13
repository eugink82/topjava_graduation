package ru.graduation.model;

import java.time.LocalDate;

public class Menu extends AbstractBaseEntity {

    private LocalDate date;

    public Menu(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
