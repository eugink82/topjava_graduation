package ru.graduation.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

public class Vote {

    @Getter
    @Setter
    private User user;

    @Getter
    @Setter
    private LocalDateTime vote_date_time;

    @Getter
    @Setter
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(User user, LocalDateTime vote_date_time, Restaurant restaurant) {
        this.user = user;
        this.vote_date_time = vote_date_time;
        this.restaurant = restaurant;
    }
}
