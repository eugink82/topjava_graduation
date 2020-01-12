package ru.graduation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
public class Vote {

    @Getter
    @Setter
    @Id
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Getter
    @Setter
    @Id
    private LocalDate vote_date;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable=false)
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(User user, LocalDate vote_date, Restaurant restaurant) {
        this.user = user;
        this.vote_date = vote_date;
        this.restaurant = restaurant;
    }
}
