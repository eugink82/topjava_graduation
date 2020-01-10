package ru.graduation.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

public class Vote {

    @Getter
    @Setter
    private User user;

    @Getter
    @Setter
    private LocalDate vote_date;

    @Getter
    @Setter
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(User user, LocalDate vote_date, Restaurant restaurant) {
        this.user = user;
        this.vote_date = vote_date;
        this.restaurant = restaurant;
    }
}
