package ru.graduation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Vote extends AbstractBaseEntity{


    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @NotNull
    @Column(name="date",columnDefinition = "date default now()")
    private LocalDate voteDate=LocalDate.now();

    @Transient
    private LocalTime voteTime=LocalTime.now();

    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable=false)
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(Restaurant restaurant) {
        this(null,restaurant);
    }

    public Vote(Integer id,Restaurant restaurant){
        super(id);
        this.restaurant=restaurant;
    }

    public Vote(Restaurant restaurant,LocalDateTime dateTimeVote) {
        this(null,restaurant,dateTimeVote);
    }

    public Vote(Integer id,Restaurant restaurant,LocalDateTime dateTimeVote){
        super(id);
        this.voteDate= dateTimeVote.toLocalDate();
        this.voteTime=dateTimeVote.toLocalTime();
        this.restaurant=restaurant;
    }


    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", user=" + user +
                ", vote_date=" + voteDate +
                ", restaurant=" + restaurant +
                '}';
    }
}
